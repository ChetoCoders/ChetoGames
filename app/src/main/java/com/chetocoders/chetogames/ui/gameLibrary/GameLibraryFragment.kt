package com.chetocoders.chetogames.ui.gameLibrary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.chetocoders.chetogames.R
import com.chetocoders.chetogames.databinding.FragmentGameLibraryBinding
import com.chetocoders.chetogames.ui.gameCatalog.GameCatalogFragmentDirections
import com.chetocoders.chetogames.ui.gameLibrary.GameLibraryViewModel.UiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class GameLibraryFragment : Fragment() {

    companion object {
        private val TAG = GameLibraryFragment::class.qualifiedName
    }

    private lateinit var binding: FragmentGameLibraryBinding

    private val viewModel: GameLibraryViewModel by viewModels()
    private lateinit var adapter: GameLibraryAdapter
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameLibraryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GameLibraryAdapter(viewModel::onGameClicked)
        binding.recyclerview.adapter = adapter
        navController = view.findNavController()

        lifecycleScope.launchWhenStarted {
            viewModel.viewState.onEach { updateUi(it) }.launchIn(this)
            viewModel.requestListGame()
        }

        binding.bottomLayout.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.gameCatalog -> {
                    navController.navigate(
                        GameLibraryFragmentDirections.actionGameLibraryFragmentToGameCatalogFragment()
                    )
                }
                R.id.addGame -> {
                    navController.navigate(
                        GameLibraryFragmentDirections.actionGameLibraryFragmentToAddGameFragment()
                    )
                }
                R.id.myLibrary -> {
                    navController.currentDestination
                }
                else -> Log.d(TAG, "Unknown menu item clicked")
            }
            true
        }
    }

    private fun updateUi(model: UiModel) {
        binding.progress.visibility = if (model is UiModel.Loading) View.VISIBLE else View.GONE
        when (model) {
            is UiModel.Content -> adapter.listGameDetail = model.gameDetails
            is UiModel.Navigation -> navController.navigate(
                GameLibraryFragmentDirections.actionGameLibraryFragmentToGameDetailFragment(
                    model.gameId!!
                )
            )
            else -> Log.d(TAG, "Loading state")
        }
    }

}

