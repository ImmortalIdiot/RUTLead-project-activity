package com.immortalidiot.rutlead.ui.components.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.immortalidiot.rutlead.ui.theme.ClassicGray
import com.immortalidiot.rutlead.ui.theme.Dimensions
import com.immortalidiot.rutlead.ui.theme.LocalDimensions

@Composable
fun PrimaryProfileDialog(
    properties: DialogProperties,
    headerText: String,
    headerTextStyle: TextStyle,
    headerTextColor: Color,
    onCancelled: () -> Unit,
    isSnackbar: Boolean,
    modifier: Modifier = Modifier,
    dimensions: Dimensions = LocalDimensions.current,
    maxHeight: Dp = dimensions.profileDialogMaxHeight,
    maxWidth: Dp = dimensions.profileDialogMaxWidth,
    snackbarHostState: SnackbarHostState? = null,
    content: @Composable () -> Unit
) {
    val scheme = MaterialTheme.colorScheme

    val roundedShape = RoundedCornerShape(dimensions.shapeXLarge)

    Dialog(
        onDismissRequest = { onCancelled() },
        properties = properties
    ) {
        Box(
            modifier = modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = modifier
                    .heightIn(max = maxHeight)
                    .widthIn(max = maxWidth)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(roundedShape)
                    .background(color = scheme.onBackground)
                    .border(
                        width = LocalDimensions.current.borderXSSmall,
                        color = ClassicGray,
                        shape = roundedShape
                    ),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = headerText,
                    style = headerTextStyle,
                    color = headerTextColor
                )
                content()
            }
            if (isSnackbar) {
                SnackbarHost(
                    modifier = modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    hostState = snackbarHostState!!
                ) {
                    Snackbar(
                        snackbarData = it,
                        containerColor = scheme.errorContainer,
                        contentColor = scheme.onPrimary,
                        dismissActionContentColor = scheme.onPrimary,
                        shape = RoundedCornerShape(LocalDimensions.current.shapeNormal)
                    )
                }
            }
        }
    }
}
