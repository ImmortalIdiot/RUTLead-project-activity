package com.immortalidiot.rutlead.presentation.viemodels.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ThemeViewModel : ViewModel() {

    private val themeFlow = ThemeManager.themeFlow

    fun onAutoTheme() { themeFlow.value = ThemeStyle.AUTO }

    fun onDarkTheme() { themeFlow.value = ThemeStyle.DARK }

    fun onLightTheme() { themeFlow.value = ThemeStyle.LIGHT }
}

object ThemeManager {
    val themeFlow: MutableStateFlow<ThemeStyle> = MutableStateFlow(ThemeStyle.AUTO)
}

enum class ThemeStyle {
    DARK,
    LIGHT,
    AUTO;
}
