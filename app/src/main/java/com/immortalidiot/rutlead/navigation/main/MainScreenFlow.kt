package com.immortalidiot.rutlead.navigation.main

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.immortalidiot.rutlead.navigation.RUTLeadScreen
import com.immortalidiot.rutlead.presentation.screens.main.JournalScreen
import com.immortalidiot.rutlead.presentation.screens.main.ProfileScreen
import com.immortalidiot.rutlead.ui.theme.ClassicColors

fun NavGraphBuilder.mainScreenFlow(
    darkTheme: Boolean,
    onThemeUpdated: () -> Unit,
    screenName: (String) -> Unit
) {
    val backgroundUserColor = ClassicColors.AvatarColor.getRandomColor()
    navigation(
        startDestination = MainScreen.JournalScreen.route,
        route = RUTLeadScreen.MainScreenFlow.route
    ) {
        composable(
            route = MainScreen.JournalScreen.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            JournalScreen(darkTheme = darkTheme)
            screenName("Журнал")
        }

        composable(
            route = MainScreen.ProfileScreen.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            ProfileScreen(
                darkTheme = darkTheme,
                onThemeUpdated = onThemeUpdated,
                colorUserAvatar = backgroundUserColor,
            )
            screenName("Профиль")
        }
    }
}
