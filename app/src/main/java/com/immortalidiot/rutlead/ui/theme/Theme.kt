package com.immortalidiot.rutlead.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val DarkColorScheme = darkColorScheme(
    onBackground = ThemeColors.Dark.background,
    surface = ThemeColors.Dark.surface,
    onSurface = ThemeColors.Dark.onSurface,
    primary = ThemeColors.Dark.primary,
    onPrimary = ThemeColors.Dark.onPrimary,
    secondary = ThemeColors.Dark.secondary,
    onSecondary = ThemeColors.Dark.onSecondary,
    tertiary = ThemeColors.Dark.textNavBar,
    onTertiary = ThemeColors.Dark.label,
    outline = ThemeColors.Dark.outline,
    errorContainer = ThemeColors.Dark.containerColorError,
    error = ThemeColors.Dark.error,
    primaryContainer = ThemeColors.Dark.container,
    onPrimaryContainer = ThemeColors.Dark.container, //
    onSecondaryContainer = ThemeColors.Dark.buttonOutline,
    scrim = ThemeColors.Dark.handle

)

val LightColorScheme = lightColorScheme(
    onBackground = ThemeColors.Light.background,
    surface = ThemeColors.Light.surface,
    onSurface = ThemeColors.Light.onSurface,
    primary = ThemeColors.Light.primary,
    onPrimary = ThemeColors.Light.onPrimary,
    secondary = ThemeColors.Light.secondary,
    onSecondary = ThemeColors.Light.onSecondary,
    tertiary = ThemeColors.Light.textNavBar,
    onTertiary = ThemeColors.Light.label,
    outline = ThemeColors.Light.outline,
    errorContainer = ThemeColors.Light.containerColorError,
    error = ThemeColors.Light.error,
    primaryContainer = ThemeColors.Light.container,
    onPrimaryContainer = ThemeColors.Light.container, //
    onSecondaryContainer = ThemeColors.Light.buttonOutline,
    scrim = ThemeColors.Light.handle

)

@Composable
fun RUTLeadTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) DarkColorScheme else LightColorScheme
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
