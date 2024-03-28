package com.immortalidiot.rutlead.navigation.auth

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.immortalidiot.rutlead.navigation.RUTLeadScreen
import com.immortalidiot.rutlead.presentation.screens.auth.LoginScreen
import com.immortalidiot.rutlead.presentation.screens.auth.ResetPassword
import com.immortalidiot.rutlead.presentation.screens.auth.SignUpScreen
import com.immortalidiot.rutlead.presentation.viemodels.auth.LoginScreenViewModel
import com.immortalidiot.rutlead.presentation.viemodels.auth.ResetPasswordViewModel
import com.immortalidiot.rutlead.presentation.viemodels.auth.SignUpViewModel

fun NavGraphBuilder.authScreenFlow(
    navController: NavHostController,
    screenName: (String) -> Unit,
) {
    navigation(
        startDestination = AuthScreen.LoginScreen.route,
        route = RUTLeadScreen.AuthScreenFlow.route
    ) {
        composable(
            route = AuthScreen.LoginScreen.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            LoginScreen(
                viewModel = LoginScreenViewModel(),
                navHostController = navController
            )
            screenName("Вход")
        }

        composable(
            route = AuthScreen.SignUpScreen.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            SignUpScreen(
                viewModel = SignUpViewModel(),
                navHostController = navController
            )
            screenName("Регистрация")
        }

        composable(
            route = AuthScreen.ResetPasswordScreen.route,
            enterTransition = { fadeIn() },
            exitTransition = { fadeOut() }
        ) {
            ResetPassword(
                viewModel = ResetPasswordViewModel(),
                navController = navController
            )
            screenName("Смена пароля")
        }
    }
}
