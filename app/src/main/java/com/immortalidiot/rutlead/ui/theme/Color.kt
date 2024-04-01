package com.immortalidiot.rutlead.ui.theme

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

val DarkWhite = Color(0xFFC7D2E5)
val ClassicWhite = Color.White

val PrimaryDarkBlue = Color(0xFF1E4A99)
val DarkBlue = Color(0xFF2552A3)
val LightBlue = Color(0xFF2A5EBD)

val ClassicGray = Color.Gray
val DarkGray = Color.DarkGray
val LightGray = Color.LightGray
val DarkLightGray = Color(0xFF878787)

val DarkBlack = Color(0xFF1C1B1B)
val LightBlack = Color(0xFF232323)

val LightRed = Color(0xFFFF5454)
val DarkRed = Color(0xFF51221f)

sealed class ClassicColors(
    private val lightBrown: Color = Color(0xFF884535),
    private val brown: Color = Color(0xFF964B00),
    private val darkBrown: Color = Color(0xFF35170C),

    private val lightRed: Color = Color(0xFFFF2400),
    val red: Color = Color(0xFFFF0000),
    val darkRed: Color = Color(0xFF8B0000),

    private val lightGreen: Color = Color(0xFF00FF00),
    private val green: Color = Color(0xFF008000),
    private val darkGreen: Color = Color(0xFF006400),

    private val lightBlue: Color = Color(0xFF87CEEB),
    private val blue: Color = Color(0xFF0000FF),
    private val darkBlue: Color = Color(0xFF00008B),

    private val lightViolet: Color = Color(0xFF8A2BE2),
    private val violet: Color = Color(0xFF800080),
    private val darkViolet: Color = Color(0xFF4B0082)
) {
    object AvatarColor : ClassicColors()

    fun getRandomColor(): Color {
        val colors = listOf(
            lightBrown, brown, darkBrown,
            lightRed, red, darkRed,
            lightGreen, green, darkGreen,
            lightBlue, blue, darkBlue,
            lightViolet, violet, darkViolet
        )
        return colors[Random.nextInt(colors.size)]
    }
}

sealed class ThemeColors(
    val header: Color,
    val surface: Color,
    val background: Color,
    val primary: Color,
    val secondary: Color,
    val content: Color,
    val outline: Color,
    val onPrimary: Color,
    val containerText: Color,
    val container: Color,
    val containerColorError: Color,
    val textColorError: Color,
    val label: Color,
    val labelText: Color,
    val cursor: Color,
    val buttonOutline: Color,
    val textSelection: Color,
    val handle: Color,
    val handleBackground: Color,
    val indicatorNavBar: Color,
    val textNavBar: Color,
    val profileContent: Color,
    val changePassword: Color,
    val error: Color,
    val onSecondary: Color,
) {
    object Light : ThemeColors(
        header = ClassicWhite,
        surface = LightBlue,
        primary = LightBlue,
        onPrimary = ClassicWhite,
        secondary = LightBlue,
        onSecondary = LightBlue,
        background = ClassicWhite,
        content = LightBlue,
        outline = Color.Transparent,
        container = ClassicWhite,
        containerText = LightBlue,
        containerColorError = LightRed,
        textColorError = Color.White,
        label = ClassicWhite,
        labelText = LightBlue,
        cursor = LightBlue,
        buttonOutline = Color.White,
        textSelection = DarkWhite,
        handle = LightGray,
        handleBackground = LightGray,
        indicatorNavBar = Color.Transparent,
        textNavBar = LightBlue,
        profileContent = LightBlue,
        changePassword = LightBlue,
        error = ClassicColors.AvatarColor.red
    )

    object Dark : ThemeColors(
        header = DarkWhite,
        surface = DarkBlack,
        primary = DarkWhite,
        onPrimary = DarkWhite,
        secondary = DarkWhite,
        onSecondary = PrimaryDarkBlue,
        background = LightBlack,
        content = DarkWhite,
        outline = PrimaryDarkBlue,
        container = LightBlack,
        containerText = DarkWhite,
        containerColorError = DarkRed,
        textColorError = DarkWhite,
        label = DarkWhite,
        labelText = DarkWhite,
        cursor = DarkBlue,
        buttonOutline = DarkBlue,
        textSelection = PrimaryDarkBlue,
        handle = DarkWhite,
        handleBackground = LightBlue,
        indicatorNavBar = Color.Transparent,
        textNavBar = DarkLightGray,
        profileContent = PrimaryDarkBlue,
        changePassword = PrimaryDarkBlue,
        error = ClassicColors.AvatarColor.darkRed
    )
}
