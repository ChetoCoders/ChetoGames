package com.chetocoders.chetogames.ui.addgame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetocoders.domain.GameMode
import com.chetocoders.domain.Genre
import com.chetocoders.domain.Platform
import com.chetocoders.usecases.GetGameModesUseCase
import com.chetocoders.usecases.GetGenresUseCase
import com.chetocoders.usecases.GetPlatformsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val getGameModesUseCase: GetGameModesUseCase
) :
    ViewModel() {

    private val _genres = MutableStateFlow(emptyList<Genre>())
    val genres: StateFlow<List<Genre>> get() = _genres

    private val _platforms = MutableStateFlow(emptyList<Platform>())
    val platforms: StateFlow<List<Platform>> get() = _platforms

    private val _gameModes = MutableStateFlow(emptyList<GameMode>())
    val gameModes: StateFlow<List<GameMode>> get() = _gameModes

    fun requestGamesData() {
        viewModelScope.launch {
            _genres.emit(withContext(Dispatchers.IO) { getGenresUseCase.invoke() })
            _platforms.emit(withContext(Dispatchers.IO) { getPlatformsUseCase.invoke() })
            _gameModes.emit(withContext(Dispatchers.IO) { getGameModesUseCase.invoke() })
        }
    }
}