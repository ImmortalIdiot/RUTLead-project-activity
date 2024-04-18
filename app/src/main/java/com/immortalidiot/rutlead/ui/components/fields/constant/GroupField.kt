package com.immortalidiot.rutlead.ui.components.fields.constant

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.immortalidiot.rutlead.ui.theme.ThemeColors
import com.immortalidiot.rutlead.ui.theme.boldLato12

@Composable
fun GroupField(
    group: String,
    groupTextStyle: TextStyle,
    modifier: Modifier
) {
    Text(
        modifier = modifier,
        text = "Студент группы: $group",
        style = groupTextStyle,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun GroupFieldPreview() {
    GroupField(
        modifier = Modifier,
        group = "УВП-212",
        groupTextStyle = boldLato12.copy(
            color = if (isSystemInDarkTheme()) {
                //TODO(): add a custom color for this type of the text
                ThemeColors.Dark.header
            } else {
                ThemeColors.Light.header
            }
        )
    )
}
