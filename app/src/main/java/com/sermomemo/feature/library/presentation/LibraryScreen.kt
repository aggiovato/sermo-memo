package com.sermomemo.feature.library.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.sermomemo.app.R
import com.sermomemo.core.designsystem.components.EmptyState
import com.sermomemo.core.designsystem.theme.SermoMemoTheme
import com.sermomemo.core.designsystem.theme.ThemePreviews

@Composable
fun LibraryScreen(modifier: Modifier = Modifier) {
    EmptyState(
        title = stringResource(R.string.library_empty_title),
        description = stringResource(R.string.library_empty_description),
        modifier = modifier,
    )
}

@ThemePreviews
@Composable
private fun LibraryScreenPreview() {
    SermoMemoTheme {
        LibraryScreen()
    }
}
