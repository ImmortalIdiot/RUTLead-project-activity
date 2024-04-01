package com.immortalidiot.rutlead.ui.components.fields

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import com.immortalidiot.rutlead.ui.theme.LocalDimensions
import com.immortalidiot.rutlead.ui.theme.mediumInter12
import com.immortalidiot.rutlead.ui.theme.mediumInter14

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentIdTextField(
    hint: String,
    modifier: Modifier = Modifier,
    value: String = "",
    onTextChange: (studentID: String) -> Unit
) {

    val dimensions = LocalDimensions.current
    val roundedShape = RoundedCornerShape(dimensions.shapeXLarge)

    val scheme = MaterialTheme.colorScheme

    val customCursorHandleColor = TextSelectionColors(
        handleColor = scheme.scrim,
        backgroundColor = scheme.tertiary
    )

    var isFocused by remember { mutableStateOf(false) }

    CompositionLocalProvider(LocalTextSelectionColors provides customCursorHandleColor) {
        PrimaryTextField(
            modifier = modifier
                .fillMaxWidth()
                .onFocusChanged {
                    isFocused = !isFocused
                }
                .border(
                    width = dimensions.borderSSmall,
                    color = scheme.outline,
                    shape = roundedShape
                ),
            value = value,
            maxTextLength = 8,
            onTextChange = {
                onTextChange(it)
            },
            label = {
                Text(
                    text = hint,
                    style = if (!isFocused || value.isNotBlank()) {
                        mediumInter12.copy(color = scheme.primary)
                    } else {
                        mediumInter14.copy(color = scheme.primary)
                    }
                )
            },
            isSingleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = scheme.primaryContainer,
                textColor = scheme.primary,
                cursorColor = scheme.onSecondary,
                unfocusedLabelColor = scheme.onPrimary,
                focusedLabelColor = scheme.onPrimary,
                focusedSupportingTextColor = Color.White,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
        )
    }
}
