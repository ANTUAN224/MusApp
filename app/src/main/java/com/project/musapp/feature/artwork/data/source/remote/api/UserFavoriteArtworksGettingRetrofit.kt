package com.project.musapp.feature.artwork.data.source.remote.api

import com.project.musapp.feature.artwork.data.model.dto.artwork.ArtworkPreviewDTO
import javax.inject.Inject

class UserFavoriteArtworksGettingRetrofit @Inject constructor(
    private val artworkApiService: ArtworkApiService
) {
    suspend fun getUserFavoriteArtworks(userToken: String): List<ArtworkPreviewDTO> {
        return artworkApiService.getUserFavoriteArtworks(headerCompanionValue = "Bearer $userToken")
            .body()!!
    }
}