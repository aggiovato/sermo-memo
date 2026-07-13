package com.sermomemo.core.navigation

import kotlinx.serialization.Serializable

/** Type-safe routes. Each nested screen carries the ids it needs, never a whole entity. */
sealed interface SermoRoute {
    @Serializable data object Today : SermoRoute

    @Serializable data object Languages : SermoRoute

    @Serializable data object Library : SermoRoute

    @Serializable data object Progress : SermoRoute

    @Serializable data object Settings : SermoRoute
}
