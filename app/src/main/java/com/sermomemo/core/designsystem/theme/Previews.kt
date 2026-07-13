package com.sermomemo.core.designsystem.theme

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

// Multipreview annotations. Compose resolves them at compile time, so they cannot take a mode
// parameter: the mode is the annotation you pick.
//
//   @LightPreview  → light only
//   @DarkPreview   → dark only
//   @ThemePreviews → both, which is the default for anything shipped in the design system
//
// docs/sermo-memo-ui.md §14.4 asks every component and screen to preview light and dark, so reach
// for @ThemePreviews unless a single mode is genuinely all that matters.

@Preview(name = "Light", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
annotation class LightPreview

@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
annotation class DarkPreview

@LightPreview
@DarkPreview
annotation class ThemePreviews

/** Largest accessibility font scale. Catches clipped labels and cramped touch targets. */
@Preview(name = "Light · large font", uiMode = UI_MODE_NIGHT_NO, showBackground = true, fontScale = 2f)
@Preview(name = "Dark · large font", uiMode = UI_MODE_NIGHT_YES, showBackground = true, fontScale = 2f)
annotation class FontScalePreviews
