package com.sermomemo.core.designsystem.theme

import androidx.compose.ui.graphics.Color

/**
 * Accent colors a user can assign to a language. Used only in small accents; they never replace
 * the theme nor lower contrast.
 */
enum class LanguageColor(val token: String, val light: Color, val dark: Color) {
    AMBER("amber", Color(0xFFC96F1A), Color(0xFFFFB86B)),
    INDIGO("indigo", Color(0xFF5364A8), Color(0xFFBAC3FF)),
    TEAL("teal", Color(0xFF3F7D6A), Color(0xFFAAD1C2)),
    ROSE("rose", Color(0xFFA8546E), Color(0xFFF0A8BF)),
    PURPLE("purple", Color(0xFF7B5DA7), Color(0xFFD5B8FF)),
    BLUE("blue", Color(0xFF3D6F9E), Color(0xFFA8CBEE)),
    OLIVE("olive", Color(0xFF697447), Color(0xFFCBD79B)),
    TERRACOTTA("terracotta", Color(0xFFA85D43), Color(0xFFFFB59A));

    companion object {
        val Default = AMBER

        fun fromToken(token: String?): LanguageColor =
            entries.firstOrNull { it.token == token } ?: Default
    }
}
