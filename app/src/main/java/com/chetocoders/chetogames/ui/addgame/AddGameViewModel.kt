package com.chetocoders.chetogames.ui.addgame

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetocoders.data.common.ResultData
import com.chetocoders.domain.GameDetail
import com.chetocoders.domain.GameMode
import com.chetocoders.domain.Genre
import com.chetocoders.domain.Platform
import com.chetocoders.usecases.AddGameUseCase
import com.chetocoders.usecases.GetGameModesUseCase
import com.chetocoders.usecases.GetGenresUseCase
import com.chetocoders.usecases.GetPlatformsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AddGameViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase,
    private val getPlatformsUseCase: GetPlatformsUseCase,
    private val getGameModesUseCase: GetGameModesUseCase,
    private val addGameUseCase: AddGameUseCase
) :
    ViewModel() {

    companion object {
        private const val TAG = "AddGameViewModel"
        private val requestDispatcher: CoroutineDispatcher = Dispatchers.IO
    }

    private val _genres = MutableStateFlow(emptyList<Genre>())
    val genres: StateFlow<List<Genre>> get() = _genres

    private val _platforms = MutableStateFlow(emptyList<Platform>())
    val platforms: StateFlow<List<Platform>> get() = _platforms

    private val _gameModes = MutableStateFlow(emptyList<GameMode>())
    val gameModes: StateFlow<List<GameMode>> get() = _gameModes

    private val _game = MutableStateFlow<ResultData<GameDetail>>(GameDetail())
    val game: StateFlow<ResultData<GameDetail>> get() = _game

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> get() = _loading

    var gameInput = GameDetail()

    suspend fun requestGamesData() {
        _loading.emit(true)
        withContext(requestDispatcher) {
            when (val resultData = getGenresUseCase.invoke()) {
                is ResultData.Success -> onSuccessGetGenres(resultData.value)
                is ResultData.Failure -> onErrorGetGenres(resultData.throwable)
            }
        }
        withContext(requestDispatcher) {
            when (val resultData = getPlatformsUseCase.invoke()) {
                is ResultData.Success -> onSuccessGetPlatforms(resultData.value)
                is ResultData.Failure -> onErrorGetPlatforms(resultData.throwable)
            }
        }
        withContext(requestDispatcher) {
            when (val resultData = getGameModesUseCase.invoke()) {
                is ResultData.Success -> onSuccessGetGameModes(resultData.value)
                is ResultData.Failure -> onErrorGetGameModes(resultData.throwable)
            }
        }
        _loading.emit(false)
    }

    private fun onSuccessGetGenres(genres: List<Genre>) {
        viewModelScope.launch(requestDispatcher) {
            _genres.emit(genres)
        }
    }

    private fun onErrorGetGenres(throwable: Throwable) {
        Log.e(
            TAG,
            if (throwable.localizedMessage.isNullOrEmpty()) "Unexpected error" else throwable.localizedMessage
        )
    }

    private fun onSuccessGetPlatforms(platforms: List<Platform>) {
        viewModelScope.launch(requestDispatcher) {
            _platforms.emit(platforms)
        }
    }

    private fun onErrorGetPlatforms(throwable: Throwable) {
        Log.e(
            TAG,
            if (throwable.localizedMessage.isNullOrEmpty()) "Unexpected error" else throwable.localizedMessage
        )
    }

    private fun onSuccessGetGameModes(gameModes: List<GameMode>) {
        viewModelScope.launch(requestDispatcher) {
            _gameModes.emit(gameModes)
        }
    }

    private fun onErrorGetGameModes(throwable: Throwable) {
        Log.e(
            TAG,
            if (throwable.localizedMessage.isNullOrEmpty()) "Unexpected error" else throwable.localizedMessage
        )
    }

    fun addGame() {
        viewModelScope.launch(requestDispatcher) {
            _loading.emit(true)
            val result = addGameUseCase.invoke(gameInput)
            _game.emit(result)
            _loading.emit(false)
        }
    }
}