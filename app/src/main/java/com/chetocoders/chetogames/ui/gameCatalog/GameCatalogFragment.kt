package com.chetocoders.chetogames.ui.gameCatalog

import GameCatalogAdapter
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.chetocoders.chetogames.databinding.FragmentGameCatalogBinding
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


        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            return
        }


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

