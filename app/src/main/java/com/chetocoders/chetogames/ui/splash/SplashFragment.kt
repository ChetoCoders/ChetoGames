package com.chetocoders.chetogames.ui.splash

import android.Manifest
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.chetocoders.chetogames.R
import com.chetocoders.chetogames.databinding.FragmentSplashBinding
import com.chetocoders.chetogames.ui.alertDialog
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


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
        // Check permission location
        getPermission()
        navController = view.findNavController()

        lifecycleScope.launchWhenStarted {
            viewModel.viewRegion.onEach { binding.textViewRegion.text = it }.launchIn(this)
            viewModel.requestRegion()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.isLoaded.onEach {
                if (it != null) {
                    if (it) navigateToMain() else showDialog()
                }
            }.launchIn(this)

            viewModel.loadGames()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun navigateToMain() {
        navController.navigate(
            R.id.action_splashFragment_to_gameCatalogFragment
        )
    }

    private fun showDialog() {
        alertDialog(this.requireContext(), getString(R.string.error_load_game), { _, which ->
            if (which == DialogInterface.BUTTON_POSITIVE)
                navigateToMain()
        },null)
    }

    private fun getPermission() {
        Dexter.withContext(context)
            .withPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    report.let {

                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(context, getString(R.string.permissions_granted), Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, getString(R.string.permissions__no_granted), Toast.LENGTH_SHORT).show()
                        }

                    }
                }

                override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest?>?, token: PermissionToken?) {
                    token?.continuePermissionRequest()
                }
            }).withErrorListener{
                Toast.makeText(context, it.name, Toast.LENGTH_SHORT).show()
            }.check()

    }
}