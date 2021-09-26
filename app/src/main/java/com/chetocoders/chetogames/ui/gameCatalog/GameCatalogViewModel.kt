package com.chetocoders.chetogames.ui.gameCatalog

import androidx.lifecycle.ViewModel
import com.chetocoders.data.common.ResultData
import com.chetocoders.domain.GameDetail
import com.chetocoders.usecases.GetGamesUseCase
import com.chetocoders.usecases.GetRegionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameCatalogViewModel @Inject constructor(private val getGamesUseCase: GetGamesUseCase, private val getRegionUseCase: GetRegionUseCase) :
    ViewModel() {


    private val _viewState = MutableStateFlow<List<GameDetail>>(emptyList())
    val viewState: StateFlow<List<GameDetail>> get() = _viewState


    suspend fun loadGames() {
        withContext(Dispatchers.IO) {
            when (val result = getGamesUseCase.invoke()) {
                is ResultData.Success -> _viewState.emit(result.value)
                is ResultData.Failure -> println(result.throwable.localizedMessage)
            }
        }

        withContext(Dispatchers.IO) {
           val result = getRegionUseCase.invoke()
                println(result)
        }
    }



    fun onMovieClicked(gameDetail: GameDetail) {
        _viewState.value = emptyList()
    }

}