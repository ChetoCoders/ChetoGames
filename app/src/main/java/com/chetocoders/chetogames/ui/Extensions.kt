package com.chetocoders.chetogames.ui

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.chetocoders.chetogames.ChetoGamesApp
import com.chetocoders.chetogames.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import kotlin.reflect.KMutableProperty1

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

@JvmName("bindingTextInput")
fun <T : Any, V : Any> TextInputEditText.binding(
    property: KMutableProperty1<T, V?>,
    reference: T?,
    value: Any
) {
    this.addTextChangedListener {
        doOnTextChanged { _, _, _, _ ->
            run {
                if (value.toString().isNotEmpty()) {
                    property.setter.call(reference, value)
                }
            }
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
