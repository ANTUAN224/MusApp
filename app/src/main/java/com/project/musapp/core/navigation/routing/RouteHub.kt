package com.project.musapp.core.navigation.routing

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
    object Home : RouteHub() {
        @Serializable
        object FavoriteArtworkShowing : RouteHub() {
            @Serializable
            object Information : RouteHub()
        }

        @Serializable
        object ArtworkInformation : RouteHub()
    }

    @Serializable
    object Collection : RouteHub() {
        @Serializable
        object Creation : RouteHub() {
            @Serializable
            object ArtworkContent : RouteHub() {
                @Serializable
                object Information : RouteHub()
            }

            @Serializable
            object Empty : RouteHub()
        }
    }

    @Serializable
    object ArtisticCulture : RouteHub() {
        @Serializable
        object TechnicalGlossary : RouteHub()

        @Serializable
        object CuriositySection : RouteHub()
    }
}