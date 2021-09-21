package com.chetocoders.chetogames.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.chetocoders.chetogames.ChetoGamesApp
import com.chetocoders.chetogames.R

@Suppress("UNCHECKED_CAST")
inline fun <reified T : ViewModel> Fragment.getViewModel(crossinline factory: () -> T): T {

    val vmFactory = object : ViewModelProvider.Factory {
        override fun <U : ViewModel> create(modelClass: Class<U>): U = factory() as U
    }

    return ViewModelProvider(this, vmFactory).get()
}

val Context.app: ChetoGamesApp
    get() = applicationContext as ChetoGamesApp

val Fragment.app: ChetoGamesApp
    get() = ((activity?.app)
        ?: IllegalStateException("Fragment needs to be attach to the activity to access the App instance"))
            as ChetoGamesApp

fun ViewGroup.inflate(): View {
    return LayoutInflater.from(context)
        .inflate(R.layout.card_view_item_game, this, false)
}
