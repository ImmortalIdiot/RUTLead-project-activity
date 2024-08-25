package com.immortalidiot.rutlead

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.immortalidiot.rutlead.navigation.RUTLeadScreenFlow
import com.immortalidiot.rutlead.navigation.navBars.BottomNavigationBar
import com.immortalidiot.rutlead.presentation.viemodels.main.ThemeViewModel
import com.immortalidiot.rutlead.providers.LocalSnackbarHostState
import com.immortalidiot.rutlead.ui.theme.ClassicColors
import com.immortalidiot.rutlead.ui.theme.RUTLeadTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val themeViewModel: ThemeViewModel by viewModels()
    private val backgroundUserColor = ClassicColors.AvatarColor.getRandomColor()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val currentTheme by themeViewModel.immutableState.collectAsState()
            val darkTheme = when (currentTheme) {
                ThemeViewModel.ThemeState.Dark -> true
                ThemeViewModel.ThemeState.Light -> false
                ThemeViewModel.ThemeState.Auto -> isSystemInDarkTheme()
            }

            RUTLeadTheme(darkTheme = darkTheme) {
                val navController = rememberNavController()
                var isNavigationBarVisible by remember { mutableStateOf(false) }

                CompositionLocalProvider(LocalSnackbarHostState provides snackbarHostState) {
                    Scaffold(
                        bottomBar = {
                            if (isNavigationBarVisible) {
                                BottomNavigationBar(navController = navController)
                            }
                        }
                    ) { padding ->
                        RUTLeadScreenFlow(
                            profileThemeViewModel = themeViewModel,
                            backgroundUserColor = backgroundUserColor,
                            darkTheme = darkTheme,
                            paddingValues = padding,
                            isNavigationBarVisible = { isNavigationBarVisible = it },
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
