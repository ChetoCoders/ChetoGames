package com.chetocoders.chetogames.ui.addgame

import android.R.layout.*
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.chetocoders.chetogames.R
import com.chetocoders.chetogames.databinding.FragmentAddgameBinding
import com.chetocoders.chetogames.ui.alertDialog
import com.chetocoders.chetogames.ui.binding
import com.chetocoders.chetogames.ui.bindingAgeRating
import com.chetocoders.domain.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*


@AndroidEntryPoint
class AddGameFragment : Fragment() {
    private lateinit var binding: FragmentAddgameBinding
    private val viewModel: AddGameViewModel by viewModels()
    private lateinit var navController: NavController

    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddgameBinding.inflate(layoutInflater)

        lifecycleScope.launch(Dispatchers.Main) {
            // Binding inputs values to gameInput
            viewModel.requestGamesData()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.toolBar.inflateMenu(R.menu.add_game)
        navController = view.findNavController()

        lifecycleScope.launchWhenStarted {
            viewModel.loading.onEach {
                // Setting the status of the progress bar
                binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }.launchIn(this)

            val date =
                DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    calendar
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, monthOfYear)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    formatDate()
                }

            binding.releasedInput.setOnClickListener {
                DatePickerDialog(
                    this@AddGameFragment.requireContext(), date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            binding.categoryAutoCompleteView.setAdapter(
                ArrayAdapter(
                    this@AddGameFragment.requireContext(),
                    simple_dropdown_item_1line,
                    GameCategory.getValues().map { it.toString() }
                )
            )

            binding.categoryAutoCompleteView.binding(
                GameDetail::category,
                viewModel.gameInput,
                GameCategory.getValues().toList(), false
            )

            viewModel.genres.onEach {
                if (it.isNotEmpty()) {
                    binding.genresAutoCompleteView.setAdapter(
                        ArrayAdapter(
                            this@AddGameFragment.requireContext(),
                            simple_dropdown_item_1line,
                            it.map { genre -> genre.name }
                        )
                    )
                    binding.genresAutoCompleteView.binding(
                        GameDetail::genres,
                        viewModel.gameInput,
                        it
                    )
                }
                binding.genres.isEnabled = it.isNotEmpty()
            }.launchIn(this)

            viewModel.platforms.onEach {
                if (it.isNotEmpty()) {
                    binding.platformsAutoCompleteView.setAdapter(
                        ArrayAdapter(
                            this@AddGameFragment.requireContext(),
                            simple_dropdown_item_1line,
                            it.map { platforms -> platforms.name }
                        )
                    )
                    binding.platformsAutoCompleteView.binding(
                        GameDetail::platforms,
                        viewModel.gameInput,
                        it
                    )
                }
                binding.platforms.isEnabled = it.isNotEmpty()
            }.launchIn(this)

            viewModel.gameModes.onEach {
                if (it.isNotEmpty()) {
                    binding.gameModesAutoCompleteView.setAdapter(
                        ArrayAdapter(
                            this@AddGameFragment.requireContext(),
                            simple_dropdown_item_1line,
                            it.map { gameModes -> gameModes.name }
                        )
                    )
                    binding.gameModesAutoCompleteView.binding(
                        GameDetail::gameModes,
                        viewModel.gameInput,
                        it
                    )
                }
                binding.gameModes.isEnabled = it.isNotEmpty()
            }.launchIn(this)

            viewModel.ageRatings.onEach {
                if (it.isNotEmpty()) {

                    binding.ageRatingsAutoCompleteView.setAdapter(
                        ArrayAdapter(
                            this@AddGameFragment.requireContext(),
                            simple_dropdown_item_1line,
                            it.map { ageRating ->  ageRating.rating.toString() }
                        )
                    )

                    binding.ageRatingsAutoCompleteView.bindingAgeRating(
                        GameDetail::ageRatings,
                        viewModel.gameInput,
                        it
                    )
                }
                binding.platforms.isEnabled = it.isNotEmpty()
            }.launchIn(this)
        }

        binding.ageRatingCategoryAutoCompleteView.setAdapter(
            ArrayAdapter(
                this@AddGameFragment.requireContext(),
                simple_dropdown_item_1line,
                AgeRatingCategory.getValues().map { it.toString() }
            )
        )

        binding.ageRatingsAutoCompleteView.isEnabled = false
        binding.ageRatingCategoryAutoCompleteView.setOnItemClickListener { _, _, i, _ ->
            run {
                lifecycleScope.launch {
                    binding.ageRatingsAutoCompleteView.isEnabled = true
                    viewModel.getAgeRatingByCategory(AgeRatingCategory.getValues()[i])
                }
            }
        }


        binding.toolBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.new_game -> {
                    alertDialog(
                        this.requireContext(),
                        getString(R.string.addGameAlertDialog),
                        { _, which ->
                            if (which == DialogInterface.BUTTON_POSITIVE)
                                saveGame()
                        },
                        null
                    )
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

    private fun formatDate() {
        val format = "dd/MM/yyyy"
        val simpleDate = SimpleDateFormat(format, Locale.getDefault())
        binding.releasedInput.setText(simpleDate.format(calendar.time))
    }

    private fun saveGame() {

        with(binding) {
            viewModel.gameInput.title = titleInput.text.toString()
            viewModel.gameInput.description = descriptionInput.text.toString()
            viewModel.gameInput.released = calendar.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()

            viewModel.addGame()
        }
    }
}

