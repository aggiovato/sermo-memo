package com.sermomemo.core.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import com.sermomemo.core.designsystem.theme.FontScalePreviews
import com.sermomemo.core.designsystem.theme.SermoMemoTheme
import com.sermomemo.core.designsystem.theme.SermoSpacing
import com.sermomemo.core.designsystem.theme.ThemePreviews

/**
 * Shown when a list has no content yet. Always offers the action that fills it, so the empty
 * screen is a starting point and not a dead end.
 */
@Composable
fun EmptyState(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    icon: ImageVector = Icons.Outlined.MenuBook,
    actionText: String? = null,
    onAction: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(SermoSpacing.Large),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SermoSpacing.Small, Alignment.CenterVertically),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(SermoSpacing.XXLarge),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
        if (actionText != null && onAction != null) {
            SermoButton(text = actionText, onClick = onAction)
        }
    }
}

/** Recoverable error. The message says what happened and what the user can do about it. */
@Composable
fun ErrorState(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    retryText: String? = null,
    onRetry: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(SermoSpacing.Large),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SermoSpacing.Small, Alignment.CenterVertically),
    ) {
        Icon(
            imageVector = Icons.Outlined.ErrorOutline,
            contentDescription = null,
            modifier = Modifier.size(SermoSpacing.XXLarge),
            tint = MaterialTheme.colorScheme.error,
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
        )
        if (retryText != null && onRetry != null) {
            SermoOutlinedButton(text = retryText, onClick = onRetry)
        }
    }
}

@ThemePreviews
@FontScalePreviews
@Composable
private fun EmptyStatePreview() {
    SermoMemoTheme {
        EmptyState(
            title = "Aún no tienes idiomas",
            description = "Crea tu primer idioma para empezar a guardar lo que aprendes.",
            actionText = "Crear idioma",
            onAction = {},
        )
    }
}

@ThemePreviews
@Composable
private fun ErrorStatePreview() {
    SermoMemoTheme {
        ErrorState(
            title = "No hemos podido abrir el archivo",
            description = "Comprueba que sigue disponible y vuelve a intentarlo. No se ha modificado nada.",
            retryText = "Reintentar",
            onRetry = {},
        )
    }
}
