package com.chetocoders.chetogames.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chetocoders.usecases.GetRegionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor( private val getRegionUseCase: GetRegionUseCase) : ViewModel() {
    private val _viewRegion: MutableStateFlow<String> = MutableStateFlow(toString())
    val viewRegion: StateFlow<String> get() = _viewRegion

    fun requestRegion() {
        viewModelScope.launch {
            _viewRegion.emit(withContext(Dispatchers.IO) { getRegionUseCase.invoke() })
        }
    }
}