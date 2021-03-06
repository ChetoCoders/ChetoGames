package com.chetocoders.chetogames.ui.gameLibrary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.chetocoders.chetogames.R
import com.chetocoders.chetogames.databinding.FragmentGameLibraryBinding
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


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameLibraryBinding.inflate(layoutInflater)
        return binding.apply {
            binding.bottomLayout.bottomNavigation.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.gameCatalog -> {
                        findNavController().navigate(
                            GameLibraryFragmentDirections.actionGameLibraryFragmentToGameCatalogFragment()
                        )
                    }
                    R.id.addGame -> {
                        findNavController().navigate(
                            GameLibraryFragmentDirections.actionGameLibraryFragmentToAddGameFragment()
                        )
                    }
                    R.id.myLibrary -> {
                        findNavController().currentDestination
                    }
                    else -> Log.d(TAG, "Unknown menu item clicked")
                }
                true
            }
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GameLibraryAdapter(viewModel::onGameClicked)
        binding.recyclerview.adapter = adapter

        binding.bottomLayout.bottomNavigation.menu.findItem(R.id.myLibrary).isChecked = true

        lifecycleScope.launchWhenStarted {
            viewModel.loading.onEach {
                // Setting the status of the progress bar
                binding.progress.visibility = if (it) View.VISIBLE else View.GONE
            }.launchIn(this)
            viewModel.requestListGame()


            viewModel.games.onEach { adapter.listGameDetail = it }.launchIn(this)

            viewModel.navigateToGameDetail.onEach { gameId ->
                if (gameId > -1) {
                    findNavController().navigate(
                        GameLibraryFragmentDirections.actionGameLibraryFragmentToGameDetailFragment(
                            gameId
                        )
                    )
                }
            }.launchIn(this)
        }
    }
}

