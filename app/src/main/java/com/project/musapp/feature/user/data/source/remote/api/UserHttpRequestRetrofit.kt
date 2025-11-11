package com.project.musapp.feature.user.data.source.remote.api

import com.project.musapp.feature.artwork.data.model.dto.artwork.ArtworkPreviewDTO
import com.project.musapp.feature.user.data.model.dto.UserProfileDTO
import javax.inject.Inject

class UserHttpRequestRetrofit @Inject constructor(
    private val userApiService: UserApiService
) {
    suspend fun getUserProfile(userToken: String) =
        userApiService.getUserProfile(
            headerCompanionValue = "Bearer $userToken"
        ).body()!!

    suspend fun addArtworkToUserFavoriteArtworks(
        userToken: String,
        artworkId: Long
    ) =
        userApiService.addArtworkToUserFavoriteArtworks(
            headerCompanionValue = "Bearer $userToken",
            artworkId = artworkId
        ).body()!!

    suspend fun deleteArtworkFromUserFavoriteArtworks(
        userToken: String,
        artworkId: Long
    ) =
        userApiService.deleteArtworkFromUserFavoriteArtworks(
            headerCompanionValue = "Bearer $userToken",
            artworkId = artworkId
        ).body()!!

    suspend fun getUserFavoriteArtworks(
        userToken: String
    ) = userApiService.getUserFavoriteArtworks(
        headerCompanionValue = "Bearer $userToken"
    ).body()!!
}