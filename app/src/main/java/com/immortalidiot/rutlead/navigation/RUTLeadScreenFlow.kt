package com.immortalidiot.rutlead.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.immortalidiot.rutlead.navigation.auth.authScreenFlow
import com.immortalidiot.rutlead.navigation.main.mainScreenFlow

@Composable
fun RUTLeadScreenFlow(
    backgroundUserColor: Color,
    darkTheme: Boolean,
    paddingValues: PaddingValues,
    isNavigationBarVisible: (Boolean) -> Unit,
    navController: NavHostController = rememberNavController()
) {
     NavHost(
         modifier = Modifier.padding(paddingValues = paddingValues),
         navController = navController,
         startDestination = RUTLeadScreen.AuthScreenFlow.route
     ) {
         authScreenFlow(
             navController = navController,
             darkTheme = darkTheme
         ) { isNavigationBarVisible(false) }
         mainScreenFlow(backgroundUserColor = backgroundUserColor) { isNavigationBarVisible(true) }
     }
}
