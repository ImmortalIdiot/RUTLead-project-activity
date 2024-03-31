package com.immortalidiot.rutlead.presentation.viemodels.main

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ProfileScreenViewModel : ViewModel() {
    @Immutable
    sealed class State {
        object AutoThemeSelected: State()
        object LightThemeSelected: State()
        object DarkThemeSelected: State()
    }

    var mutableState = MutableStateFlow<State>(State.AutoThemeSelected)
        private set

    fun onAutoTheme() {
        mutableState.update {
            State.AutoThemeSelected
        }
    }

    fun onDarkTheme() {
        mutableState.update {
            State.DarkThemeSelected
        }
    }

    fun onLightTheme() {
        mutableState.update {
            State.LightThemeSelected
        }
    }
}
