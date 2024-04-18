package com.immortalidiot.rutlead.ui.components.fields

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import com.immortalidiot.rutlead.ui.theme.LocalDimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryTextField(
    colors: TextFieldColors,
    modifier: Modifier,
    value: String = "",
    isSingleLine: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    minTextLength: Int = 0,
    maxTextLength: Int = Int.MAX_VALUE,
    label: @Composable (() -> Unit)? = {},
    trailingIcon: @Composable (() -> Unit)? = null,
    onTextChange: (String) -> Unit,
) {
    val dimensions = LocalDimensions.current
    val colorScheme = MaterialTheme.colorScheme

    var localValue by rememberSaveable { mutableStateOf(value = value) }

    val customCursorHandleColor = TextSelectionColors(
        handleColor = colorScheme.scrim,
        backgroundColor = colorScheme.onBackground
    )

    CompositionLocalProvider(LocalTextSelectionColors provides customCursorHandleColor) {
        TextField(
            modifier = modifier,
            value = localValue,
            onValueChange = { text ->
                if (text.length <= maxTextLength) {
                    localValue = text
                    onTextChange(text)
                }
            },
            label = label,
            trailingIcon = trailingIcon,
            singleLine = isSingleLine,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            colors = colors,
            shape = RoundedCornerShape(dimensions.shapeXLarge)
        )
    }
}
