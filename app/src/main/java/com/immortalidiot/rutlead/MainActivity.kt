package com.immortalidiot.rutlead

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.rememberNavController
import com.immortalidiot.rutlead.navigation.RUTLeadScreenFlow
import com.immortalidiot.rutlead.navigation.navBars.BottomNavigationBar
import com.immortalidiot.rutlead.providers.LocalSnackbarHostState
import com.immortalidiot.rutlead.ui.theme.RUTLeadTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val theme = isSystemInDarkTheme()
            var darkTheme by remember { mutableStateOf( theme ) }
            RUTLeadTheme(darkTheme = darkTheme) {
                val snackbarHostState = remember { SnackbarHostState() }
                val navController = rememberNavController()
                var isNavigationBarVisible by remember { mutableStateOf(false) }

                CompositionLocalProvider(LocalSnackbarHostState provides snackbarHostState) {
                    Scaffold(
                        bottomBar = {
                            if (isNavigationBarVisible) {
                                BottomNavigationBar(
                                    darkTheme = darkTheme,
                                    navController = navController
                                )
                            }
                        }
                    ) { padding ->
                        RUTLeadScreenFlow(
                            darkTheme = darkTheme,
                            paddingValues = padding,
                            isNavigationBarVisible = { isNavigationBarVisible = it },
                            onThemeUpdated = { darkTheme = !darkTheme},
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
