package com.chetocoders.chetogames.ui.gameLibrary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetocoders.chetogames.di.IoDispatcher
import com.chetocoders.domain.GameDetail
import com.chetocoders.usecases.GetGamesUseCase
import com.chetocoders.usecases.GetLocalGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameLibraryViewModel @Inject constructor(
    @IoDispatcher private val requestDispatcher: CoroutineDispatcher,
    private val getLocalGamesUseCaseProvider: GetLocalGamesUseCase
) : ViewModel() {

    private val _viewState: MutableStateFlow<UiModel> = MutableStateFlow(UiModel.Loading)
    val viewState: StateFlow<UiModel> get() = _viewState

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val gameDetails: List<GameDetail>) : UiModel()
        class Navigation(val gameId : Long?) : UiModel()
    }

    fun requestListGame() {
        viewModelScope.launch {
            _viewState.emit(withContext(requestDispatcher) { UiModel.Loading })
            _viewState.emit(withContext(requestDispatcher) { UiModel.Content(getLocalGamesUseCaseProvider.invoke()) })
        }
    }

    fun onGameClicked(gameDetail: GameDetail) {
        _viewState.value = UiModel.Loading
        _viewState.value = UiModel.Navigation(gameDetail.id)
    }

}