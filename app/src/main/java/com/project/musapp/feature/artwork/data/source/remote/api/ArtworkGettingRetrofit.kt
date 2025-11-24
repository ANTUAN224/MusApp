package com.project.musapp.feature.artwork.data.source.remote.api

import javax.inject.Inject

class ArtworkGettingRetrofit @Inject constructor(
    private val artworkApiService: ArtworkApiService
) {
    suspend fun getUserFavoriteArtworks(userToken: String) =
        artworkApiService.getUserFavoriteArtworks(
            headerCompanionValue = "Bearer $userToken"
        ).body()!!

    suspend fun getCollectionArtworks(userToken: String, collectionId: Long) =
        artworkApiService.getCollectionArtworks(
            headerCompanionValue = "Bearer $userToken",
            collectionId = collectionId
        ).body()!!

    suspend fun getArtwork(userToken: String, artworkId: Long) =
        artworkApiService.getArtwork(
            headerCompanionValue = "Bearer $userToken",
            artworkId = artworkId
        ).body()!!

    suspend fun getSearchArtworks(userToken: String) =
        artworkApiService.getSearchArtworks(
            headerCompanionValue = "Bearer $userToken"
        ).body()!!
}