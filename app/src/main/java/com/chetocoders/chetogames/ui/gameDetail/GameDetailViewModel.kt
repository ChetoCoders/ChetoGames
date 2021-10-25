package com.chetocoders.chetogames.ui.gameDetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetocoders.data.common.ResultData
import com.chetocoders.domain.GameDetail
import com.chetocoders.usecases.GetGameUseCase
import com.chetocoders.usecases.UpdateGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val getGameUseCase: GetGameUseCase,
    private val updateGameUseCase: UpdateGameUseCase
) : ViewModel() {

    companion object {
        private val TAG = GameDetailViewModel::class.qualifiedName
        private val requestDispatcher: CoroutineDispatcher = Dispatchers.IO
    }

    /** StateFlow to indicate the selected game */
    private val _game = MutableStateFlow(GameDetail())
    val game: StateFlow<GameDetail> get() = _game

    /** StateFlow to indicate when io thread is loading data */
    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> get() = _loading

    suspend fun getGame(gameId: Long) {

        withContext(requestDispatcher) {
            when (val result = getGameUseCase.invoke(gameId)) {
                is ResultData.Success -> onSuccessGetGame(result.value)
                is ResultData.Failure -> onErrorGetGame(result.throwable)
            }

        }
    }

    suspend fun updateFavourite() {
        withContext(requestDispatcher) {
            val gameValue = _game.value
            gameValue.isFavourite = !gameValue.isFavourite

            Log.i("UPDATING", "Updating game")

            when (val result = updateGameUseCase.invoke(gameValue)) {
                is ResultData.Success -> onSuccessGetGame(result.value)
                is ResultData.Failure -> onErrorGetGame(result.throwable)
            }
        }
    }

    /**
     * Function to handle when getUserByID request succeeds
     *
     * @param user Selected user
     */
    private fun onSuccessGetGame(game: GameDetail) {
        viewModelScope.launch(requestDispatcher) {
            Log.i("GAME", "Emiting game")
            _game.emit(game)
        }
    }

    /**
     * Function to handle when getUserByID request fails
     *
     * @param throwable Exception
     */
    private fun onErrorGetGame(throwable: Throwable) {
        Log.e(
            TAG,
            if (throwable.localizedMessage.isNullOrEmpty()) "Unexpected error" else throwable.localizedMessage
        )
    }
}