package com.immortalidiot.rutlead.navigation.main

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.immortalidiot.rutlead.navigation.RUTLeadScreen
import com.immortalidiot.rutlead.presentation.screens.main.JournalScreen
import com.immortalidiot.rutlead.presentation.screens.main.ProfileScreen
import com.immortalidiot.rutlead.presentation.viemodels.main.ProfileScreenViewModel
import com.immortalidiot.rutlead.presentation.viemodels.main.ThemeViewModel

fun NavGraphBuilder.mainScreenFlow(
    navController: NavHostController,
    backgroundUserColor: Color,
    screenName: (String) -> Unit
) {
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
            val themeViewModel: ThemeViewModel = viewModel()
            val profileScreenViewModel: ProfileScreenViewModel = viewModel()

            ProfileScreen(
                colorUserAvatar = backgroundUserColor,
                themeViewModel = themeViewModel,
                profileScreenViewModel = profileScreenViewModel,
                navController = navController
            )
            screenName("Профиль")
        }
    }
}
