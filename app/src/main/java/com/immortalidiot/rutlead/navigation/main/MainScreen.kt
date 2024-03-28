package com.immortalidiot.rutlead.navigation.main

import androidx.compose.runtime.Immutable

@Immutable
sealed class MainScreen (val route: String) {
    object JournalScreen: MainScreen("Журнал")
    object ProfileScreen: MainScreen("Профиль")
}
