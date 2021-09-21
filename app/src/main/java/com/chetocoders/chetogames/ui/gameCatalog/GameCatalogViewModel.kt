package com.chetocoders.chetogames.ui.gameCatalog

import androidx.lifecycle.ViewModel
import com.chetocoders.data.common.ResultData
import com.chetocoders.usecases.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameCatalogViewModel @Inject constructor(private val getGamesUseCase: GetGamesUseCase) : ViewModel() {

    suspend fun loadGames() {
        withContext(Dispatchers.IO) {
            when (val result = getGamesUseCase.invoke()) {
                is ResultData.Success -> println(result.value)
                is ResultData.Failure -> println(result.throwable.localizedMessage)
            }
        }
    }
}