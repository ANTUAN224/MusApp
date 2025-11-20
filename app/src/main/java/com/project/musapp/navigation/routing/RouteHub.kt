package com.project.musapp.navigation.routing

import kotlinx.serialization.Serializable

@Serializable
sealed class RouteHub {
    @Serializable
    data object UserStateInitialChecking : RouteHub()

    @Serializable
    data object InitialMenu : RouteHub()

    @Serializable
    data object Registration : RouteHub()

    @Serializable
    data class Home(
        val artworkId: Long? = null,
        val addArtworkToUserFavoriteArtworks: Boolean = false
    ) : RouteHub()

    @Serializable
    data class Artwork(val artworkId: Long) : RouteHub()

    @Serializable
    data object Collection : RouteHub() {
        @Serializable
        data object Previews: RouteHub()

        @Serializable
        data class Artworks(val collectionId: Long, val collectionTitle: String) : RouteHub()
    }

    @Serializable
    data object ArtisticCulture : RouteHub() {
        @Serializable
        data object TechnicalGlossary : RouteHub()

        @Serializable
        data object CuriositySection : RouteHub()
    }
}