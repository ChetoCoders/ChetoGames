package com.chetocoders.chetogames.ui.gameCatalog

import GameCatalogAdapter
import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.chetocoders.chetogames.R
import com.chetocoders.chetogames.databinding.FragmentGameCatalogBinding
import com.chetocoders.chetogames.ui.gameCatalog.GameCatalogViewModel.UiModel
import com.chetocoders.chetogames.ui.gameDetail.GameDetailFragment.Companion.GAME_ID
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class GameCatalogFragment : Fragment() {

    private var binding: FragmentGameCatalogBinding? = null

    private val viewModel: GameCatalogViewModel by viewModels()
    private lateinit var adapter: GameCatalogAdapter
    private lateinit var navController: NavController

    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameCatalogBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GameCatalogAdapter(viewModel::onGameClicked)
        binding?.recyclerview?.adapter = adapter
        navController = view.findNavController()

        lifecycleScope.launchWhenStarted {
            viewModel.viewState.onEach { updateUi(it) }.launchIn(this)
            viewModel.requestListGame()
        }


        binding?.bottomLayout?.bottomNavigation?.setOnItemSelectedListener {
            when (it.itemId) {
               R.id.addGame -> navController.navigate(R.id.action_gameCatalogFragment_to_addGameFragment)
                R.id.gameCatalog -> Unit
                R.id.myLibrary -> Unit
                else -> Unit
            }

            true
        }
    }

    private fun updateUi(model: UiModel) {
        binding?.progress?.visibility = if (model is UiModel.Loading) View.VISIBLE else View.GONE
        when (model) {
            is UiModel.Content -> adapter.listGameDetail = model.gameDetails
            is UiModel.Navigation -> navController.navigate(
                R.id.action_gameCatalogFragment_to_gameDetailFragment, bundleOf("GAME_ID" to model.gameId)
            )
        }
    }

}

