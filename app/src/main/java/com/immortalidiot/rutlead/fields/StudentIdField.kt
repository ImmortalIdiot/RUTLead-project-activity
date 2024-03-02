package com.immortalidiot.rutlead.fields

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.immortalidiot.rutlead.ui.theme.DarkBlue
import com.immortalidiot.rutlead.ui.theme.InterFontFamily
import com.immortalidiot.rutlead.ui.theme.LightBlue
import com.immortalidiot.rutlead.ui.theme.boldInter14
import com.immortalidiot.rutlead.ui.theme.boldInter36
import com.immortalidiot.rutlead.ui.theme.mediumInter14

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentIdTextField(type: String) {
    var text by remember { mutableStateOf(TextFieldValue()) }
    var isFocused by remember { mutableStateOf(false) }
    val textLength = 6
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged { isFocused = !isFocused }
            .border(BorderStroke(3.dp, Color.Transparent),
                shape = RoundedCornerShape(15.dp)),
        value = text,
        onValueChange = {
                        if (it.text.length <= textLength) text = it
                        },
        textStyle = boldInter14,
        label = { Text(type,
            style = if (!isFocused) TextStyle(
                fontSize = 10.sp,
                fontFamily = InterFontFamily
            )
            else TextStyle(
                fontSize = 14.sp,
                fontFamily = InterFontFamily
            )
            ) },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            textColor = Color.Black,
            cursorColor = LightBlue,
            unfocusedLabelColor = LightBlue,
            focusedLabelColor = DarkBlue,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedTrailingIconColor = LightBlue,
            unfocusedTrailingIconColor = LightBlue
        ),

        shape = RoundedCornerShape(15.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}