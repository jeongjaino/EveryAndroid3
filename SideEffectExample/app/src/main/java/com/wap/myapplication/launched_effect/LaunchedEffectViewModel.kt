package com.wap.myapplication.launched_effect

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LaunchedEffectViewModel: ViewModel() {

    private val _sharedFlow = MutableSharedFlow<ScreenEvents>()
    val sharedFlow = _sharedFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            _sharedFlow.emit(ScreenEvents.ShowSnackbar("네트워크 연결이 불안정합니다."))
        }
    }

    sealed class ScreenEvents {
        data class ShowSnackbar(val message: String): ScreenEvents()
        data class Navigate(val route: String): ScreenEvents()
    }
}