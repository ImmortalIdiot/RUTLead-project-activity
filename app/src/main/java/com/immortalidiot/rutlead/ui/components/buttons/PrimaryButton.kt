package com.immortalidiot.rutlead.ui.components.buttons

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.immortalidiot.rutlead.ui.theme.LocalDimensions
import com.immortalidiot.rutlead.ui.theme.ThemeColors
import com.immortalidiot.rutlead.ui.theme.boldLato20

@Composable
fun PrimaryButton(
    modifier: Modifier,
    scheme: ColorScheme,
    text: String,
    textStyle: TextStyle = boldLato20,
    colorText: Color = scheme.primary,
    containerColor: Color = scheme.primaryContainer,
    outlineColor: Color = scheme.outline,
    onButtonClick: () -> Unit
) {
    val dimensions = LocalDimensions.current
    val roundedShape = RoundedCornerShape(dimensions.shapeSLarge)

    Button(
        onClick = { onButtonClick() },
        modifier = modifier.border(
            width = dimensions.borderSSmall,
            shape = roundedShape,
            color = outlineColor
        ),
        shape = roundedShape,
        colors = ButtonDefaults.buttonColors(containerColor = containerColor)
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = text,
                style = textStyle,
                color = colorText
            )
        }
    }
}
