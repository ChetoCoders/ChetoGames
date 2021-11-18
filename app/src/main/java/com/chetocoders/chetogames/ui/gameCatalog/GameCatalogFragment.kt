package com.chetocoders.chetogames.ui.gameCatalog

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameCatalogBinding.inflate(layoutInflater)
        return binding.apply {
            binding.bottomLayout.bottomNavigation.setOnItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.gameCatalog -> {
                        findNavController().currentDestination
                    }
                    R.id.addGame -> {
                        val addGameAction =
                            GameCatalogFragmentDirections.actionGameLibraryFragmentToAddGameFragment()
                        findNavController().navigate(addGameAction)
                    }
                    R.id.myLibrary -> {
                        val myLibraryAction =
                            GameCatalogFragmentDirections.actionGameCatalogFragmentToGameLibraryFragment()
                        findNavController().navigate(
                            myLibraryAction
                        )
                    }
                    else -> Log.d(TAG, "Unknown menu item clicked")
                }
                true
            }
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GameCatalogAdapter(viewModel::onGameClicked)
        binding.recyclerview.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.loading.onEach {
                // Setting the status of the progress bar
                binding.progress.visibility = if (it) View.VISIBLE else View.GONE
            }.launchIn(this)
            viewModel.requestListGame()

            viewModel.games.onEach { adapter.listGameDetail = it }.launchIn(this)

            viewModel.navigateToGameDetail.onEach { gameId ->
                if (gameId > -1) {
                    val navigateAction =
                        GameCatalogFragmentDirections.actionGameCatalogFragmentToGameDetailFragment(
                            gameId
                        )
                    findNavController().navigate(navigateAction)
                }
            }.launchIn(this)
        }
    }
}

