package com.chetocoders.chetogames.ui

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chetocoders.chetogames.ChetoGamesApp
import com.chetocoders.chetogames.R
import com.chetocoders.domain.AgeRating
import com.chetocoders.domain.GameCategory
import com.chetocoders.domain.Rating
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import kotlin.properties.Delegates
import kotlin.reflect.KMutableProperty1


val Context.app: ChetoGamesApp
    get() = applicationContext as ChetoGamesApp

val Fragment.app: ChetoGamesApp
    get() = ((activity?.app)
        ?: IllegalStateException("Fragment needs to be attach to the activity to access the App instance"))
            as ChetoGamesApp

fun ViewGroup.inflate(cardViewItemGame: Int, b: Boolean): View {
    return LayoutInflater.from(context)
        .inflate(R.layout.card_view_item_game, this, false)
}

fun GameCategory.getString() = when (index.toInt()) {
    0 -> R.string.category_main_game
    1 -> R.string.category_dlc_addon
    2 -> R.string.category_expansion
    3 -> R.string.category_bundle
    4 -> R.string.category_standalone_expansion
    5 -> R.string.category_mod
    6 -> R.string.category_episode
    7 -> R.string.category_season
    8 -> R.string.category_remake
    9 -> R.string.category_remaster
    10 -> R.string.category_expanded_game
    11 -> R.string.category_port
    12 -> R.string.category_fork
    else -> -1
}

fun Rating.getDrawable() = when (index.toInt()) {
    1 -> R.drawable.pegi_3
    2 -> R.drawable.pegi_7
    3 -> R.drawable.pegi_12
    4 -> R.drawable.pegi_16
    5 -> R.drawable.pegi_18
    6 -> R.drawable.esrb_rp
    7 -> R.drawable.esrb_ec
    8 -> R.drawable.esrb_e
    9 -> R.drawable.esrb_e10
    10 -> R.drawable.esrb_t
    11 -> R.drawable.esrb_m
    12 -> R.drawable.esrb_d
    else -> null
}

fun ImageView.loadUrl(url: String) {
    Glide.with(this).load(url).placeholder(R.drawable.no_image_avaible).into(this)
}

inline fun <VH : RecyclerView.ViewHolder, T> RecyclerView.Adapter<VH>.basicDiffUtil(
    initialValue: List<T>,
    crossinline areItemsTheSame: (T, T) -> Boolean = { old, new -> old == new },
    crossinline areContentsTheSame: (T, T) -> Boolean = { old, new -> old == new }
) =
    Delegates.observable(initialValue) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                areItemsTheSame(old[oldItemPosition], new[newItemPosition])

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                areContentsTheSame(old[oldItemPosition], new[newItemPosition])

            override fun getOldListSize(): Int = old.size

            override fun getNewListSize(): Int = new.size
        }).dispatchUpdatesTo(this@basicDiffUtil)
    }

@JvmName("bindingAutoComplete")
fun <T : Any, V : Any> AutoCompleteTextView.binding(
    property: KMutableProperty1<T, V?>,
    reference: T?,
    list: List<Any>,
    isTypeList: Boolean = true
) {
    this.setOnItemClickListener { _, _, i, _ ->
        run {
            property.setter.call(reference, if (isTypeList) listOf(list[i]) else list[i])
        }
    }
}

@JvmName("bindingAutoCompleteAgeRating")
fun <T : Any, V : Any> AutoCompleteTextView.bindingAgeRating(
    property: KMutableProperty1<T, V?>,
    reference: T?,
    list: List<Any>
) {
    this.setOnItemClickListener { _, _, i, _ ->
        run {
            property.setter.call(reference, listOf(list.find { Rating.getValues()[i] == (it as AgeRating).rating}))
        }
    }
}

fun alertDialog(
    context: Context,
    message: String,
    okListener: DialogInterface.OnClickListener?,
    cancelListener: DialogInterface.OnClickListener?
) {
    MaterialAlertDialogBuilder(context)
        .setMessage(message)
        .setCancelable(false)
        .setNegativeButton(android.R.string.cancel, cancelListener)
        .setPositiveButton(android.R.string.ok, okListener)
        .show()
}
