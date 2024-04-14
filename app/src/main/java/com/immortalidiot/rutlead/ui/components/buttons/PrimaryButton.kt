package com.immortalidiot.rutlead.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import com.immortalidiot.rutlead.ui.theme.Dimensions
import com.immortalidiot.rutlead.ui.theme.LocalDimensions
import com.immortalidiot.rutlead.ui.theme.boldLato20

@Composable
fun PrimaryButton(
    modifier: Modifier,
    maxHeight: Dp? = null,
    maxWidth: Dp? = null,
    borderWidth: Dp? = dimensions.borderSSmall,
    shape: RoundedCornerShape = RoundedCornerShape(dimensions.shapeSLarge),
    scheme: ColorScheme,
    text: String,
    textStyle: TextStyle = boldLato20,
    colorText: Color = scheme.primary,
    containerColor: Color = scheme.primaryContainer,
    outlineColor: Color = scheme.outline,
    backgroundColor: Color = scheme.onBackground,
    onButtonClick: () -> Unit
) {
    val dimensions = LocalDimensions.current

    val currentModifier = modifier
        .clip(shape = shape)
        .border(
            width = borderWidth,
            shape = shape,
            color = outlineColor
        )
        .background(color = backgroundColor)
        .apply {
            if (maxHeight != null) {
                heightIn(max = maxHeight)
            }
            if (maxWidth != null) {
                widthIn(max = maxWidth)
            }
        }

    Button(
        onClick = { onButtonClick() },
        modifier = currentModifier,
        shape = shape,
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
