package com.sermomemo.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalSemanticColors = staticCompositionLocalOf { LightSemanticColors }

/**
 * Entry point of the SermoMemo Design System (SMDS).
 *
 * Dynamic color stays off: the warm identity is part of the product and must not be replaced by
 * the wallpaper palette.
 */
@Composable
fun SermoMemoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) SermoDarkColorScheme else SermoLightColorScheme
    val semanticColors = if (darkTheme) DarkSemanticColors else LightSemanticColors

    CompositionLocalProvider(LocalSemanticColors provides semanticColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = SermoTypography,
            shapes = SermoShapes,
            content = content,
        )
    }
}

/** Access to state colors from any composable: `SermoTheme.semanticColors.strong`. */
object SermoTheme {
    val semanticColors: SermoSemanticColors
        @Composable get() = LocalSemanticColors.current
}
