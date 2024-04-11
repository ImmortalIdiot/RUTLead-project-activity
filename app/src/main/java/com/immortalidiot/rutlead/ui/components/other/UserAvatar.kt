package com.immortalidiot.rutlead.ui.components.other

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.immortalidiot.rutlead.ui.theme.ClassicColors
import com.immortalidiot.rutlead.ui.theme.ThemeColors
import com.immortalidiot.rutlead.ui.theme.mediumInter32

@Composable
fun UserAvatar(
    modifier: Modifier,
    url: String? = null,
    initials: String = "ФИ",
    backgroundColor: Color,
    textStyle: TextStyle,
    onIconClick: () -> Unit
) {
    //TODO(): add the ability to load photo from gallery
    Box(
        modifier = modifier
            .size(80.dp)
            .clickable {
                onIconClick()
            },
        contentAlignment = Alignment.Center
    ) {
        //TODO(): load photo if the user has one
        Image(
            modifier = modifier.clip(CircleShape),
            painter = ColorPainter(backgroundColor),
            contentDescription = "avatar"
        )
        Canvas(modifier = modifier) {
            drawCircle(SolidColor(backgroundColor))
        }
        Text(
            text = initials.uppercase(),
            style = textStyle,
        )
    }
}

@Preview
@Composable
fun UserAvatarPreview() {
    val isDarkTheme = isSystemInDarkTheme()

    UserAvatar(
        modifier = Modifier,
        initials = "КМ",
        textStyle = mediumInter32.copy(
            color =
            if (isDarkTheme) {
                ThemeColors.Dark.onPrimary
            } else {
                ThemeColors.Light.onPrimary
            }
        ),
        backgroundColor = ClassicColors.AvatarColor.getRandomColor(),
        onIconClick = {}
    )
}
