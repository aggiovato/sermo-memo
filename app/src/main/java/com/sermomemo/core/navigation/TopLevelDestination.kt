package com.sermomemo.core.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.Today
import androidx.compose.material.icons.outlined.TrendingUp
import androidx.compose.ui.graphics.vector.ImageVector
import com.sermomemo.app.R

/**
 * The four bottom-bar destinations. Settings is reachable from the top bar and deliberately does
 * not take a permanent slot.
 */
enum class TopLevelDestination(
    val route: SermoRoute,
    val icon: ImageVector,
    @param:StringRes val labelRes: Int,
) {
    TODAY(SermoRoute.Today, Icons.Outlined.Today, R.string.destination_today),
    LANGUAGES(SermoRoute.Languages, Icons.Outlined.Language, R.string.destination_languages),
    LIBRARY(SermoRoute.Library, Icons.Outlined.Book, R.string.destination_library),
    PROGRESS(SermoRoute.Progress, Icons.Outlined.TrendingUp, R.string.destination_progress),
}
