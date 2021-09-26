package com.chetocoders.chetogames.ui.addgame

import android.R.layout.*
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.chetocoders.chetogames.R
import com.chetocoders.chetogames.databinding.FragmentAddgameBinding
import com.chetocoders.domain.AgeRatingCategory
import com.chetocoders.domain.Rating
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class AddGameFragment : Fragment() {
    private lateinit var binding: FragmentAddgameBinding
    private val viewModel: AddGameViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddgameBinding.inflate(layoutInflater)
        viewModel.requestGamesData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolBar.inflateMenu(R.menu.add_game)
        navController = view.findNavController()
        lifecycleScope.launchWhenStarted {
            viewModel.genres.onEach {
                binding.genresAutoCompleteView.setAdapter(
                    ArrayAdapter(
                        this@AddGameFragment.requireContext(),
                        simple_dropdown_item_1line,
                        it.map { genre -> genre.name }
                    )
                )
            }.launchIn(this)
            viewModel.platforms.onEach {
                binding.platformsAutoCompleteView.setAdapter(
                    ArrayAdapter(
                        this@AddGameFragment.requireContext(),
                        simple_dropdown_item_1line,
                        it.map { platforms -> platforms.name }
                    )
                )
            }.launchIn(this)
            viewModel.gameModes.onEach {
                binding.gameModesAutoCompleteView.setAdapter(
                    ArrayAdapter(
                        this@AddGameFragment.requireContext(),
                        simple_dropdown_item_1line,
                        it.map { gameModes -> gameModes.name }
                    )
                )
            }.launchIn(this)
        }


        binding.ageRatingsAutoCompleteView.setAdapter(
            ArrayAdapter(
                this@AddGameFragment.requireContext(),
                simple_dropdown_item_1line,
                Rating.getValues().map { it.toString() }
            )
        )

        binding.ageRatingCategoryAutoCompleteView.setAdapter(
            ArrayAdapter(
                this@AddGameFragment.requireContext(),
                simple_dropdown_item_1line,
                AgeRatingCategory.getValues().map { it.toString() }
            )
        )

        binding.toolBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.new_game -> {
                    Log.d("ADDGAME", "Click button")
                    true
                }
                else -> false
            }
        }

        binding.toolBar.setNavigationOnClickListener {
            navController.navigate(
                R.id.action_addGameFragment_to_gameCatalogFragment
            )
        }

        super.onViewCreated(view, savedInstanceState)
    }
}