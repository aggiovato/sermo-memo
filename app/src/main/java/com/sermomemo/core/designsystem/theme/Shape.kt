package com.sermomemo.core.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// Radius tokens from docs/sermo-memo-master.md §36. `Full` is reserved for chips, states and
// avatars: it must not be applied to every component.
object SermoRadius {
    val None = RoundedCornerShape(0.dp)
    val ExtraSmall = RoundedCornerShape(4.dp)
    val Small = RoundedCornerShape(8.dp)
    val Medium = RoundedCornerShape(12.dp)
    val Large = RoundedCornerShape(16.dp)
    val ExtraLarge = RoundedCornerShape(24.dp)
    val Full = RoundedCornerShape(999.dp)
}

val SermoShapes = Shapes(
    extraSmall = SermoRadius.ExtraSmall,
    small = SermoRadius.Small,
    medium = SermoRadius.Medium,
    large = SermoRadius.Large,
    extraLarge = SermoRadius.ExtraLarge,
)
