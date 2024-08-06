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

    fun onAutoTheme() { mutableState.value = ThemeState.Auto }

    fun onDarkTheme() { mutableState.value = ThemeState.Dark }

    fun onLightTheme() { mutableState.value = ThemeState.Light }
}
