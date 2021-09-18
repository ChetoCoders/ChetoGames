package com.chetocoders.chetogames.ui.gameCatalog

import com.chetocoders.usecases.GetGames
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameCatalogViewModel @Inject constructor(private val getGames: GetGames) : ViewModel() {

    fun onGameRequest() {
        viewModelScope.launch {
            val listgame = withContext(Dispatchers.IO) { getGames.invoke() } }
    }
}