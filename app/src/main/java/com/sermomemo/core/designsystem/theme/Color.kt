package com.sermomemo.core.designsystem.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Base palette. See docs/sermo-memo-master.md §30–§33.

private val AmberPrimaryLight = Color(0xFFC96F1A)
private val AmberPrimaryDark = Color(0xFFFFB86B)

val SermoLightColorScheme = lightColorScheme(
    primary = AmberPrimaryLight,
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFFFE2C3),
    onPrimaryContainer = Color(0xFF4A2808),
    secondary = Color(0xFF5364A8),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFE0E5FF),
    onSecondaryContainer = Color(0xFF17204C),
    tertiary = Color(0xFF3F7D6A),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFC8EDDF),
    onTertiaryContainer = Color(0xFF092F26),
    error = Color(0xFFB94A48),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD8),
    onErrorContainer = Color(0xFF410006),
    background = Color(0xFFFCF8F4),
    onBackground = Color(0xFF211A16),
    surface = Color(0xFFFFFBF8),
    onSurface = Color(0xFF211A16),
    surfaceVariant = Color(0xFFF2E8DF),
    onSurfaceVariant = Color(0xFF51453D),
    outline = Color(0xFF85756A),
    outlineVariant = Color(0xFFD8C8BD),
    inverseSurface = Color(0xFF372F2A),
    inverseOnSurface = Color(0xFFFCEFE8),
    scrim = Color(0xFF000000),
)

val SermoDarkColorScheme = darkColorScheme(
    primary = AmberPrimaryDark,
    onPrimary = Color(0xFF512900),
    primaryContainer = Color(0xFF743B00),
    onPrimaryContainer = Color(0xFFFFE2C3),
    secondary = Color(0xFFBAC3FF),
    onSecondary = Color(0xFF24306B),
    secondaryContainer = Color(0xFF3A477F),
    onSecondaryContainer = Color(0xFFE0E5FF),
    tertiary = Color(0xFFAAD1C2),
    onTertiary = Color(0xFF12382E),
    tertiaryContainer = Color(0xFF2A5045),
    onTertiaryContainer = Color(0xFFC8EDDF),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    background = Color(0xFF19120E),
    onBackground = Color(0xFFF0DFD6),
    surface = Color(0xFF211914),
    onSurface = Color(0xFFF0DFD6),
    surfaceVariant = Color(0xFF51453D),
    onSurfaceVariant = Color(0xFFD8C8BD),
    outline = Color(0xFFA08E82),
    outlineVariant = Color(0xFF51453D),
    inverseSurface = Color(0xFFF0DFD6),
    inverseOnSurface = Color(0xFF372F2A),
    scrim = Color(0xFF000000),
)
