package com.immortalidiot.rutlead.presentation.viemodels.main

import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ThemeViewModel : ViewModel() {

    @Immutable
    sealed class ThemeState {
        object Auto : ThemeState()
        object Light : ThemeState()
        object Dark : ThemeState()
    }

    private val mutableState = MutableStateFlow<ThemeState>(ThemeState.Auto)
    val immutableState: StateFlow<ThemeState> = mutableState.asStateFlow()

    private val themeFlow = ThemeManager.themeFlow

    fun onAutoTheme() {
        mutableState.value = ThemeState.Auto
        themeFlow.value = ThemeStyle.AUTO
    }

    fun onDarkTheme() {
        mutableState.value = ThemeState.Dark
        themeFlow.value = ThemeStyle.DARK
    }

    fun onLightTheme() {
        mutableState.value = ThemeState.Light
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
