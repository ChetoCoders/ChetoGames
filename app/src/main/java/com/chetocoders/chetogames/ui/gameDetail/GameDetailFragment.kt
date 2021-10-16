package com.chetocoders.chetogames.ui.gameDetail

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.bold
import androidx.core.text.italic
import androidx.core.view.updatePadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.chetocoders.chetogames.R.color
import com.chetocoders.chetogames.R.drawable
import com.chetocoders.chetogames.databinding.FragmentGameDetailBinding
import com.chetocoders.chetogames.ui.getDrawable
import com.chetocoders.chetogames.ui.getString
import com.chetocoders.domain.GameCategory
import com.chetocoders.domain.Rating
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import java.time.format.DateTimeFormatter


@AndroidEntryPoint
class GameDetailFragment : Fragment() {

    companion object {
        private val TAG = GameDetailFragment::class.qualifiedName
    }

    private val viewModel: GameDetailViewModel by viewModels()
    private lateinit var binding: FragmentGameDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).apply {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            val drawable = ResourcesCompat.getDrawable(resources, drawable.ic_arrow_back, null)
            drawable!!.colorFilter = PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN)
            supportActionBar?.setHomeAsUpIndicator(drawable)
        }

        lifecycleScope.launchWhenCreated {
            observeGame()

            Log.i(TAG, "Loading - requesting game")
            viewModel.getGame(114285)
        }

        binding.fab.setOnClickListener { lifecycleScope.launch(Dispatchers.IO) {viewModel.updateFavourite()} }
    }

    private suspend fun CoroutineScope.observeGame() {
        viewModel.game.onEach {
            Log.d(TAG, "BINDING - binding game")
            binding.toolbarLayout.title = it.title
            binding.toolbarLayout.setExpandedTitleColor(Color.WHITE)
            binding.toolbarLayout.setCollapsedTitleTextColor(Color.WHITE)
            binding.container.description.text = it.description

            val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy ")

            it.released?.let { released -> binding.container.releaseDate.text = formatter.format(released) }

            it.category?.let { category ->
                if (category != GameCategory.MAIN_GAME) {
                    binding.container.category.text = SpannableStringBuilder().italic {
                        bold { append(it.title) }.append(" is a ${getString(category.getString()) }")
                    }
                    binding.container.category.visibility = View.VISIBLE
                }
            }
            context?.let { context ->
                if (it.cover != null) {
                    Glide.with(context)
                        .load("https:${it.cover!!.url?.replace("thumb", "screenshot_med")}")
                        .into(binding.cover)
                }
            }

            it.ageRatings?.forEach { ageRating -> binding.ratingList.addView(buildRating(ageRating.rating)) }

            it.platforms?.forEach { platform ->
                binding.container.chipGroupPlatforms.addView(
                    buildChip(
                        platform.name!!
                    )
                )
            }
            it.genres?.forEach { genres -> binding.container.chipGroupGenre.addView(buildChip(genres.name!!)) }
            it.gameModes?.forEach { gameModes -> binding.container.chipGroupGamemode.addView(
                    buildChip( gameModes.name!! )
            )}

            val list = mutableListOf<CarouselItem>()
            it.screenshots?.forEach { image ->
                list.add(CarouselItem( "https:${ image.url?.replace("thumb","720p")}"))
            }
            binding.container.carousel.setData(list)

            if (it.isFavourite) binding.fab.drawable.setTint(Color.RED) else binding.fab.drawable.setTint(Color.WHITE)
        }.launchIn(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "BINDING - OnDestroy")
    }

    private fun buildRating(rating: Rating?): View? {
        val imageView = ImageView(context)
        imageView.layoutParams = ViewGroup.LayoutParams(
            60,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        imageView.updatePadding(10, 10, 0, 0)

        imageView.setImageDrawable(context?.let {
            ContextCompat.getDrawable(it, rating?.getDrawable()!!)
        })
        return imageView
    }


    private fun buildChip(text: String): Chip {
        val chip = Chip(context)
        chip.text = text
        chip.setChipBackgroundColorResource(color.teal_200)
        return chip
    }

}