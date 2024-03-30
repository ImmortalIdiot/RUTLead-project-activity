package com.immortalidiot.rutlead.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.immortalidiot.rutlead.ui.theme.ThemeColors

@Composable
fun JournalScreen(
    darkTheme: Boolean,
    modifier: Modifier = Modifier,
    //TODO(): add viewmodel
) {
    val palette = if (isSystemInDarkTheme()) ThemeColors.Dark else ThemeColors.Light

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(palette.backgroundScreen),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Журнал")
    }
}

//@Preview
//@Composable
//fun JournalScreenPreview() {
//    JournalScreen()
//}
