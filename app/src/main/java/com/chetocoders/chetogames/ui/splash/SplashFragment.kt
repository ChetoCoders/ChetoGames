package com.chetocoders.chetogames.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.chetocoders.chetogames.R
import com.chetocoders.chetogames.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private val viewModel: SplashViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var zoomIn: Animation

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater)
        zoomIn = AnimationUtils.loadAnimation(this.requireContext(), R.anim.zoom_in)
        binding.imageView.animation = zoomIn
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = view.findNavController()
        lifecycleScope.launchWhenStarted {
            delay(2000)
            navController.navigate(
                R.id.action_splashFragment_to_gameCatalogFragment
            )
        }
        super.onViewCreated(view, savedInstanceState)
    }
}