package com.chetocoders.chetogames.ui.gameCatalog

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
import com.chetocoders.chetogames.databinding.FragmentGameCatalogBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class GameCatalogFragment : Fragment() {

    companion object {
        private val TAG = GameCatalogFragment::class.qualifiedName
    }

    private lateinit var binding: FragmentGameCatalogBinding

    private val viewModel: GameCatalogViewModel by viewModels()
    private lateinit var adapter: GameCatalogAdapter
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameCatalogBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GameCatalogAdapter(viewModel::onGameClicked)
        binding.recyclerview.adapter = adapter
        navController = view.findNavController()

        lifecycleScope.launchWhenStarted {
            viewModel.loading.onEach {
                // Setting the status of the progress bar
                binding.progress.visibility = if (it) View.VISIBLE else View.GONE
            }.launchIn(this)
            viewModel.requestListGame()


            viewModel.games.onEach { adapter.listGameDetail = it }.launchIn(this)

            viewModel.navigateToGameDetail.onEach { gameId ->
                if (gameId > -1) {
                    navController.navigate(
                        GameCatalogFragmentDirections.actionGameCatalogFragmentToGameDetailFragment(
                            gameId
                        )
                    )
                }
            }.launchIn(this)
        }

        binding.bottomLayout.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.gameCatalog -> {
                    navController.currentDestination
                }
                R.id.addGame -> {
                    navController.navigate(
                        GameCatalogFragmentDirections.actionGameLibraryFragmentToAddGameFragment()
                    )
                }
                R.id.myLibrary -> {
                    navController.navigate(
                        GameCatalogFragmentDirections.actionGameCatalogFragmentToGameLibraryFragment()
                    )
                }
                else -> Log.d(TAG, "Unknown menu item clicked")
            }
            true
        }
    }
}

