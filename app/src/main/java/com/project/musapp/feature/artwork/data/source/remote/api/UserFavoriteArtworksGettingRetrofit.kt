package com.project.musapp.feature.artwork.data.source.remote.api

import com.project.musapp.feature.artwork.data.model.dto.artwork.ArtworkPreviewDTO
import com.project.musapp.feature.profile.data.source.remote.api.UserProfileApiService
import javax.inject.Inject

class UserFavoriteArtworksGettingRetrofit @Inject constructor(
    private val userProfileApiService: UserProfileApiService
) {
    suspend fun getUserFavoriteArtworks(userToken: String): List<ArtworkPreviewDTO> {
        return userProfileApiService.getUserFavoriteArtworks(headerCompanionValue = "Bearer $userToken")
            .body()!!
    }
}