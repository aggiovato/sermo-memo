package com.sermomemo.core.designsystem.theme

import androidx.compose.ui.graphics.Color

/**
 * Colors for learning states and answer feedback.
 *
 * These are never the sole carrier of meaning: every state also ships a label and an icon.
 */
data class SermoSemanticColors(
    val new: Color,
    val learning: Color,
    val reviewing: Color,
    val strong: Color,
    val relearning: Color,
    val suspended: Color,
    val correct: Color,
    val hard: Color,
    val incorrect: Color,
    val info: Color,
)

val LightSemanticColors = SermoSemanticColors(
    new = Color(0xFF5364A8),
    learning = Color(0xFFC96F1A),
    reviewing = Color(0xFF7B5DA7),
    strong = Color(0xFF3F7D6A),
    relearning = Color(0xFFB65E3C),
    suspended = Color(0xFF6E625A),
    correct = Color(0xFF3F7D6A),
    hard = Color(0xFFB7791F),
    incorrect = Color(0xFFB94A48),
    info = Color(0xFF5364A8),
)

val DarkSemanticColors = SermoSemanticColors(
    new = Color(0xFFBAC3FF),
    learning = Color(0xFFFFB86B),
    reviewing = Color(0xFFD5B8FF),
    strong = Color(0xFFAAD1C2),
    relearning = Color(0xFFFFB59A),
    suspended = Color(0xFFBFB2AA),
    correct = Color(0xFFAAD1C2),
    hard = Color(0xFFF5C06A),
    incorrect = Color(0xFFFFB4AB),
    info = Color(0xFFBAC3FF),
)
