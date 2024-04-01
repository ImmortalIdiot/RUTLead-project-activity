package com.immortalidiot.rutlead.ui.components.other

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.immortalidiot.rutlead.ui.theme.boldInter34

@Composable
fun BoxLabel(text: String) {
    val scheme = MaterialTheme.colorScheme
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth(),
        color = scheme.onPrimary,
        textAlign = TextAlign.Center,
        style = boldInter34
    )
}
