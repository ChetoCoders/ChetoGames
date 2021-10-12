package com.chetocoders.chetogames.ui.gameCatalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetocoders.domain.GameDetail
import com.chetocoders.usecases.GetGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameCatalogViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    private val _viewState: MutableStateFlow<UiModel> = MutableStateFlow(UiModel.Loading)
    val viewState: StateFlow<UiModel> get() = _viewState

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val gameDetails: List<GameDetail>) : UiModel()
        class Navigation(val gameDetail: GameDetail) : UiModel()
    }

    fun requestListGame() {
        viewModelScope.launch {
            _viewState.emit(withContext(Dispatchers.IO) { UiModel.Loading })
            _viewState.emit(withContext(Dispatchers.IO) { UiModel.Content(getGamesUseCase.invoke()) })
        }
    }

    fun onMovieClicked(gameDetail: GameDetail) {
        _viewState.value = UiModel.Loading
    }

}