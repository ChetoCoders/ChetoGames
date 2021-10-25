package com.chetocoders.chetogames.ui.gameCatalog

import GameCatalogAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.chetocoders.chetogames.databinding.FragmentGameCatalogBinding
import com.chetocoders.chetogames.ui.gameCatalog.GameCatalogViewModel.UiModel
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class GameCatalogFragment : Fragment() {

    private var binding: FragmentGameCatalogBinding? = null

    private val viewModel: GameCatalogViewModel by viewModels()
    private lateinit var adapter: GameCatalogAdapter

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
        adapter = GameCatalogAdapter(viewModel::onMovieClicked)
        binding?.recyclerview?.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.viewState.onEach { updateUi(it) }.launchIn(this)
            viewModel.requestListGame()
        }
    }

    private fun updateUi(model: UiModel) {
        binding?.progress?.visibility = if (model is UiModel.Loading) View.VISIBLE else View.GONE
        when (model) {
            is UiModel.Content -> adapter.listGameDetail = model.gameDetails
        }
    }

}

