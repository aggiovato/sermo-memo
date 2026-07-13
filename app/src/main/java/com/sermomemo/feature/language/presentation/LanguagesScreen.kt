package com.sermomemo.feature.language.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sermomemo.app.R
import com.sermomemo.core.designsystem.components.EmptyState
import com.sermomemo.core.designsystem.theme.SermoMemoTheme
import com.sermomemo.core.designsystem.theme.ThemePreviews

@Composable
fun LanguagesScreen(modifier: Modifier = Modifier) {
    EmptyState(
        title = stringResource(R.string.languages_empty_title),
        description = stringResource(R.string.languages_empty_description),
        modifier = modifier,
    )
}

@ThemePreviews
@Composable
private fun LanguagesScreenPreview() {
    SermoMemoTheme {
        LanguagesScreen()
    }
}
