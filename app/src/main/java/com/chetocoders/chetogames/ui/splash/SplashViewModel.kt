package com.chetocoders.chetogames.ui.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetocoders.chetogames.di.IoDispatcher
import com.chetocoders.data.common.ResultData
import com.chetocoders.usecases.GetRegionUseCase
import com.chetocoders.usecases.LoadGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    @IoDispatcher private val requestDispatcher: CoroutineDispatcher,
    private val loadGamesUseCase: LoadGamesUseCase,
    private val getRegionUseCase: GetRegionUseCase
) : ViewModel() {

    companion object {
        private val TAG = SplashViewModel::class.qualifiedName
    }

    private val _isLoaded: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val isLoaded: StateFlow<Boolean?> get() = _isLoaded

    private val _viewRegion: MutableStateFlow<String> = MutableStateFlow(toString())
    val viewRegion: StateFlow<String> get() = _viewRegion

    fun loadGames() {
        viewModelScope.launch {
            withContext(requestDispatcher) {
                when (val resultData = loadGamesUseCase.invoke()) {
                    is ResultData.Success -> onSuccessLoadGames(resultData.value)
                    is ResultData.Failure -> onErrorLoadGames(resultData.throwable)
                } }
        }
    }

    private suspend fun onSuccessLoadGames(value: Boolean) {
        Log.i(TAG, "On Success load")
        _isLoaded.emit(value)
    }

    private suspend fun onErrorLoadGames(throwable: Throwable) {
        Log.e(
            TAG,
            if (throwable.localizedMessage.isNullOrEmpty()) "Unexpected error" else throwable.localizedMessage
        )

        _isLoaded.emit(false)
    }

    fun requestRegion() {
        viewModelScope.launch {
            _viewRegion.emit(withContext(requestDispatcher) { getRegionUseCase.invoke() })
        }
    }
}