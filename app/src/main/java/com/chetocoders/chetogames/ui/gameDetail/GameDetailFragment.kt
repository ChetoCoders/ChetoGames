package com.chetocoders.chetogames.ui.gameDetail

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.bold
import androidx.core.text.italic
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.chetocoders.chetogames.R
import com.chetocoders.chetogames.R.color
import com.chetocoders.chetogames.R.drawable
import com.chetocoders.chetogames.databinding.FragmentGameDetailBinding
import com.chetocoders.chetogames.di.IoDispatcher
import com.chetocoders.chetogames.ui.UiConstants
import com.chetocoders.chetogames.ui.getDrawable
import com.chetocoders.chetogames.ui.getString
import com.chetocoders.domain.GameCategory
import com.chetocoders.domain.Rating
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import java.time.format.DateTimeFormatter
import javax.inject.Inject

/**
 * Game detail fragment
 *
 * @constructor Create empty Game detail fragment
 */
@AndroidEntryPoint
class GameDetailFragment : Fragment() {

    companion object {
        private val TAG = GameDetailFragment::class.qualifiedName
        private const val RATING_PADDING = 10
        private const val RATING_WIDTH = 60
    }

    /** View model */
    private val viewModel: GameDetailViewModel by viewModels()

    /** View binding */
    private lateinit var binding: FragmentGameDetailBinding

    /** Get passed arguments */
    private val args: GameDetailFragmentArgs by navArgs()

    /** Inject dispatcher */
    @Inject
    @IoDispatcher
    lateinit var requestDispatcher: CoroutineDispatcher

    /**
     * On create view
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    /**
     * On view created
     *
     * @param view
     * @param savedInstanceState
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            val drawable = ResourcesCompat.getDrawable(resources, drawable.ic_arrow_back, null)
            drawable!!.colorFilter = PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
            supportActionBar?.setHomeAsUpIndicator(drawable)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            observeGame()

            viewModel.getGame(args.gameId)
        }

        binding.fab.setOnClickListener {
            lifecycleScope.launch(requestDispatcher) {
                viewModel.updateFavourite()
            }
        }
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    /**
     *  Function to observe the game stateFlow
     *
     */
    private fun CoroutineScope.observeGame() {
        viewModel.game.onEach {
            if (it != null) {
                binding.toolbarLayout.title = it.title
                binding.toolbarLayout.setExpandedTitleColor(Color.WHITE)
                binding.toolbarLayout.setCollapsedTitleTextColor(Color.WHITE)
                binding.container.description.text = it.description

                val formatter: DateTimeFormatter =
                    DateTimeFormatter.ofPattern(UiConstants.DATE_PATTERN)

                it.released?.let { released ->
                    binding.container.releaseDate.text = formatter.format(released)
                }

                it.category?.let { category ->
                    if (category != GameCategory.MAIN_GAME) {
                        binding.container.category.text = SpannableStringBuilder().italic {
                            bold { append(it.title) }.append(
                                " ${getString(R.string.game_detail_category_indicator)} ${
                                    getString(
                                        category.getString()
                                    )
                                }"
                            )
                        }
                        binding.container.category.visibility = View.VISIBLE
                    }
                }
                context?.let { context ->
                    if (it.cover != null) {
                        Glide.with(context)
                            .load(
                                "${UiConstants.HTTPS}:${
                                    it.cover!!.url?.replace(
                                        UiConstants.IMAGE_THUMB,
                                        UiConstants.IMAGE_SCREENSHOT_MED
                                    )
                                }"
                            )
                            .into(binding.cover)
                    }
                }

                binding.ratingList.removeAllViews()
                it.ageRatings?.forEach { ageRating ->
                    binding.ratingList.addView(
                        buildRating(
                            ageRating.rating
                        )
                    )
                }

                binding.container.chipGroupPlatforms.removeAllViews()
                it.platforms?.forEach { platform ->
                    binding.container.chipGroupPlatforms.addView(
                        buildChip(
                            platform.name!!
                        )
                    )
                }

                binding.container.chipGroupGenre.removeAllViews()
                it.genres?.forEach { genres ->
                    binding.container.chipGroupGenre.addView(
                        buildChip(
                            genres.name!!
                        )
                    )
                }

                binding.container.chipGroupGamemode.removeAllViews()
                it.gameModes?.forEach { gameModes ->
                    binding.container.chipGroupGamemode.addView(
                        buildChip(gameModes.name!!)
                    )
                }

                val list = mutableListOf<CarouselItem>()
                it.screenshots?.forEach { image ->
                    list.add(
                        CarouselItem(
                            "${UiConstants.HTTPS}:${
                                image.url?.replace(
                                    UiConstants.IMAGE_THUMB,
                                    UiConstants.IMAGE_720P
                                )
                            }"
                        )
                    )
                }
                binding.container.carousel.setData(list)

                if (it.isFavourite) binding.fab.drawable.setTint(Color.RED) else binding.fab.drawable.setTint(
                    Color.WHITE
                )
            }
        }.launchIn(this)
    }

    /**
     * Function to build rating image view
     *
     * @param rating Rating
     * @return view
     */
    private fun buildRating(rating: Rating?): View {
        val imageView = ImageView(context)
        imageView.layoutParams = ViewGroup.LayoutParams(
            RATING_WIDTH,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        imageView.updatePadding(RATING_PADDING, RATING_PADDING, 0, 0)

        imageView.setImageDrawable(context?.let {
            ContextCompat.getDrawable(it, rating?.getDrawable()!!)
        })
        return imageView
    }

    /**
     * Function to build chip from text
     *
     * @param text name's chip
     * @return chip
     */
    private fun buildChip(text: String): Chip {
        val chip = Chip(context)
        chip.text = text
        chip.setChipBackgroundColorResource(color.teal_200)
        return chip
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }
}