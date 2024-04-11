package com.immortalidiot.rutlead

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.immortalidiot.rutlead.presentation.viemodels.main.ThemeManager
import com.immortalidiot.rutlead.presentation.viemodels.main.ThemeStyle
import com.immortalidiot.rutlead.providers.LocalSnackbarHostState
import com.immortalidiot.rutlead.ui.theme.ClassicColors
import com.immortalidiot.rutlead.ui.theme.RUTLeadTheme

class MainActivity : ComponentActivity() {
    private val themeFlow = ThemeManager.themeFlow
    private val backgroundUserColor = ClassicColors.AvatarColor.getRandomColor()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val currentTheme by themeFlow.collectAsState()
            val darkTheme = when (currentTheme) {
                ThemeStyle.DARK -> true
                ThemeStyle.LIGHT -> false
                ThemeStyle.AUTO -> isSystemInDarkTheme()
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
