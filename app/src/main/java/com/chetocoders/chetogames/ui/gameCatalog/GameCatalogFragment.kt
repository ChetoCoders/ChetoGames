package com.chetocoders.chetogames.ui.gameCatalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chetocoders.chetogames.databinding.FragmentGameCatalogBinding
import com.chetocoders.chetogames.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GameCatalogFragment : Fragment() {

    private var binding: FragmentGameCatalogBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameCatalogBinding.inflate(layoutInflater)
        return binding?.root
    }
}