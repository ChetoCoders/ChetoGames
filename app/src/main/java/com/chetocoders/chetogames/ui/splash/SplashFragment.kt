package com.chetocoders.chetogames.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chetocoders.chetogames.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : AppCompatActivity() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentSplashBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}