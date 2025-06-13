package com.project.musapp.core.feature.navigation.routing

import kotlinx.serialization.Serializable

@Serializable
sealed class RouteHub {
    @Serializable
    object UserInitialChecking : RouteHub()

    @Serializable
    object InitialMenu : RouteHub()

    @Serializable
    object Registration : RouteHub() {
        @Serializable
        object StepOne : RouteHub()

        @Serializable
        object StepTwo : RouteHub()
    }

    @Serializable
    object Home : RouteHub() {
        @Serializable
        object RecentVisitedArtworkShowing : RouteHub() {
            @Serializable
            object Information : RouteHub()
        }

        @Serializable
        object AnyArtworkSearched : RouteHub()

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