package com.chetocoders.chetogames.ui.gameCatalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetocoders.chetogames.di.IoDispatcher
import com.chetocoders.domain.GameDetail
import com.chetocoders.usecases.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameCatalogViewModel @Inject constructor(
    @IoDispatcher private val requestDispatcher: CoroutineDispatcher,
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    private val _games = MutableStateFlow<List<GameDetail>>(emptyList())
    val games: StateFlow<List<GameDetail>> get() = _games

    private val _loading = MutableStateFlow(false)
    val loading: MutableStateFlow<Boolean> get() = _loading

    private val _navigateToGameDetail = MutableStateFlow(-1L)
    val navigateToGameDetail: StateFlow<Long> get() = _navigateToGameDetail


    fun requestListGame() {
        viewModelScope.launch {
            _navigateToGameDetail.value = -1L
            _loading.value = true
            _games.emit(withContext(requestDispatcher) {getGamesUseCase.invoke()})
            _loading.value = false
        }
    }

    fun onGameClicked(gameDetail: GameDetail) {
        _navigateToGameDetail.value = gameDetail.id!!
    }

}