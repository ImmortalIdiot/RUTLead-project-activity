package com.immortalidiot.rutlead.presentation.viemodels.main

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ThemeViewModel : ViewModel() {

    private val themeFlow = ThemeManager.themeFlow
    @Immutable
    sealed class State {
        object AutoThemeSelected : State()
        object LightThemeSelected : State()
        object DarkThemeSelected : State()
    }

    var mutableState = MutableStateFlow<State>(State.AutoThemeSelected)
        private set

    fun onAutoTheme() {
        mutableState.value = State.AutoThemeSelected
        themeFlow.value = ThemeStyle.AUTO
    }

    fun onDarkTheme() {
        mutableState.value = State.DarkThemeSelected
        themeFlow.value = ThemeStyle.DARK
    }

    fun onLightTheme() {
        mutableState.value = State.LightThemeSelected
        themeFlow.value = ThemeStyle.LIGHT
    }
}

object ThemeManager {
    val themeFlow: MutableStateFlow<ThemeStyle> = MutableStateFlow(ThemeStyle.AUTO)
}

enum class ThemeStyle {
    DARK,
    LIGHT,
    AUTO;
}
