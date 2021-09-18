package com.chetocoders.chetogames.ui.addgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.chetocoders.chetogames.R
import com.chetocoders.chetogames.databinding.FragmentAddgameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddGameFragment : Fragment() {
    private var binding: FragmentAddgameBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddgameBinding.inflate(layoutInflater)
        return binding?.root
    }
}