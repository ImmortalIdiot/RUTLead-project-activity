package com.immortalidiot.rutlead.ui.components.fields.constant

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.immortalidiot.rutlead.ui.theme.ThemeColors
import com.immortalidiot.rutlead.ui.theme.boldInter16

@Composable
fun FullNameField(
    fullNameTextStyle: TextStyle,
    modifier: Modifier,
    firstName: String = "Фамилия",
    secondName: String = "Имя",
    thirdName: String = "Отчество"
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "$firstName $secondName",
            style = fullNameTextStyle,
            textAlign = TextAlign.Center
        )
        Text(
            text = thirdName,
            style = fullNameTextStyle
        )
    }
}

@Preview
@Composable
fun FullNameFieldPreview() {
    FullNameField(
        modifier = Modifier,
        fullNameTextStyle = boldInter16.copy(
            color = if (isSystemInDarkTheme()) {
                ThemeColors.Dark.header
            } else {
                ThemeColors.Light.header
            }
        )
    )
}
