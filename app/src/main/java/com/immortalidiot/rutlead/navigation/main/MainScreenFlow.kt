package com.immortalidiot.rutlead.navigation.main

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.immortalidiot.rutlead.navigation.RUTLeadScreen
import com.immortalidiot.rutlead.presentation.screens.main.JournalScreen
import com.immortalidiot.rutlead.presentation.screens.main.ProfileScreen

fun NavGraphBuilder.mainScreenFlow(screenName: (String) -> Unit) {
    navigation(
        startDestination = MainScreen.JournalScreen.route,
        route = RUTLeadScreen.MainScreenFlow.route
    ) {
        composable(
            route = MainScreen.JournalScreen.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            JournalScreen()
            screenName("Журнал")
        }

        composable(
            route = MainScreen.ProfileScreen.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            ProfileScreen()
            screenName("Профиль")
        }
    }
}
