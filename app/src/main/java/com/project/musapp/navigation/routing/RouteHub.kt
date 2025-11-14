package com.project.musapp.navigation.routing

import kotlinx.serialization.Serializable

@Serializable
sealed class RouteHub {
    @Serializable
    object UserStateInitialChecking : RouteHub()

    @Serializable
    object InitialMenu : RouteHub()

    @Serializable
    object Registration : RouteHub()

    @Serializable
    data class Home(
        val artworkId: Long? = null,
        val addArtworkToUserFavoriteArtworks: Boolean = false
    ) : RouteHub()

    @Serializable
    data class Artwork(val artworkId: Long) : RouteHub()

    @Serializable
    object Collection : RouteHub() {
        @Serializable
        object Artwork : RouteHub()
    }

    @Serializable
    object ArtisticCulture : RouteHub() {
        @Serializable
        object TechnicalGlossary : RouteHub()

        @Serializable
        object CuriositySection : RouteHub()
    }
}