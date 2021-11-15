package com.chetocoders.chetogames.ui.gameLibrary

import GameLibraryAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.chetocoders.chetogames.R
import com.chetocoders.chetogames.databinding.FragmentGameLibraryBinding
import com.chetocoders.chetogames.ui.gameLibrary.GameLibraryViewModel.UiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class GameLibraryFragment : Fragment() {

    private var binding: FragmentGameLibraryBinding? = null

    private val viewModel: GameLibraryViewModel by viewModels()
    private lateinit var adapter: GameLibraryAdapter
    private lateinit var navController: NavController



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameLibraryBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = GameLibraryAdapter(viewModel::onGameClicked)
        binding?.recyclerview?.adapter = adapter
        navController = view.findNavController()

        lifecycleScope.launchWhenStarted {
            viewModel.viewState.onEach { updateUi(it) }.launchIn(this)
            viewModel.requestListGame()
        }
    }

    private fun updateUi(model: UiModel) {
        binding?.progress?.visibility = if (model is UiModel.Loading) View.VISIBLE else View.GONE
        when (model) {
            is UiModel.Content -> adapter.listGameDetail = model.gameDetails
            is UiModel.Navigation -> navController.navigate(
                R.id.action_gameLibraryFragment_to_gameDetailFragment, bundleOf("GAME_ID" to model.gameId)
            )
        }
    }

}

