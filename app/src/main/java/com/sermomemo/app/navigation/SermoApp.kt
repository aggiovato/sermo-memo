package com.sermomemo.app.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sermomemo.core.navigation.SermoRoute
import com.sermomemo.core.navigation.TopLevelDestination
import com.sermomemo.app.R
import com.sermomemo.feature.language.presentation.LanguagesScreen
import com.sermomemo.feature.library.presentation.LibraryScreen
import com.sermomemo.feature.progress.presentation.ProgressScreen
import com.sermomemo.feature.settings.presentation.SettingsScreen
import com.sermomemo.feature.today.presentation.TodayScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SermoApp() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination

    val currentTopLevel = TopLevelDestination.entries.firstOrNull { destination ->
        currentDestination?.hierarchyHasRoute(destination)== true
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = currentTopLevel
                            ?.let { stringResource(it.labelRes) }
                            ?: stringResource(R.string.app_name),
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate(SermoRoute.Settings) }) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = stringResource(R.string.destination_settings),
                        )
                    }
                },
            )
        },
        bottomBar = {
            NavigationBar {
                TopLevelDestination.entries.forEach { destination ->
                    NavigationBarItem(
                        selected = destination == currentTopLevel,
                        onClick = { navController.navigateToTopLevel(destination) },
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = null,
                            )
                        },
                        label = { Text(stringResource(destination.labelRes)) },
                    )
                }
            }
        },
        floatingActionButton = {
            val fab = currentTopLevel?.primaryAction() ?: return@Scaffold
            FloatingActionButton(onClick = { /* Wired up per feature in phase 1. */ }) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(fab),
                )
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = SermoRoute.Today,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable<SermoRoute.Today> { TodayScreen() }
            composable<SermoRoute.Languages> { LanguagesScreen() }
            composable<SermoRoute.Library> { LibraryScreen() }
            composable<SermoRoute.Progress> { ProgressScreen() }
            composable<SermoRoute.Settings> { SettingsScreen() }
        }
    }
}

/** The FAB carries the primary action of the current tab. docs/sermo-memo-ui.md §5.2. */
private fun TopLevelDestination.primaryAction(): Int? = when (this) {
    TopLevelDestination.TODAY -> R.string.action_quick_review
    TopLevelDestination.LANGUAGES -> R.string.action_add_language
    TopLevelDestination.LIBRARY -> R.string.action_add_concept
    TopLevelDestination.PROGRESS -> null
}

private fun androidx.navigation.NavDestination.hierarchyHasRoute(
    destination: TopLevelDestination,
): Boolean = hierarchy.any { node ->
    when (destination.route) {
        SermoRoute.Today -> node.hasRoute<SermoRoute.Today>()
        SermoRoute.Languages -> node.hasRoute<SermoRoute.Languages>()
        SermoRoute.Library -> node.hasRoute<SermoRoute.Library>()
        SermoRoute.Progress -> node.hasRoute<SermoRoute.Progress>()
        else -> false
    }
}

private fun androidx.navigation.NavHostController.navigateToTopLevel(
    destination: TopLevelDestination,
) {
    navigate(destination.route) {
        popUpTo(graph.findStartDestination().id) { saveState = true }
        launchSingleTop = true
        restoreState = true
    }
}
