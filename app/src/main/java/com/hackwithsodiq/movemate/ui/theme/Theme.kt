package com.hackwithsodiq.movemate.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColors(
    primary = Purple80,
    secondary = Purple80,
    )

private val LightColorScheme = lightColors(
    primary = Purple40,
    secondary = PurpleGrey40,
    background = OffWhite
)

@Composable
fun MovemateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()

    val colorScheme = if (darkTheme) {
        systemUiController.setSystemBarsColor(CoolBlack)
        DarkColorScheme
    } else {
        systemUiController.setStatusBarColor(Purple40)
        LightColorScheme
    }
    MaterialTheme(
        colors = colorScheme,
        typography = Typography,
        content = content
    )
}