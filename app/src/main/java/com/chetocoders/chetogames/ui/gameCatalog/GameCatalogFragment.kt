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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class GameCatalogFragment : Fragment() {

    private var binding: FragmentGameCatalogBinding? = null

    private val viewModel : GameCatalogViewModel by viewModels()
    private lateinit var adapter: GameCatalogAdapter

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
        pepe();

        lifecycleScope.launchWhenCreated {
            viewModel.loadGames()
        }


    }

    private fun pepe() {
        viewModel.viewState.onEach {
          adapter.listGameDetail = it
        }.launchIn(lifecycleScope)
    }


}

