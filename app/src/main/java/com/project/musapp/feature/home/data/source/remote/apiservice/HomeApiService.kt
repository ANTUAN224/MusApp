package com.project.musapp.feature.home.data.source.remote.apiservice

import com.project.musapp.feature.home.data.model.dto.ArtworkPreviewDTO
import com.project.musapp.feature.home.data.model.dto.UserProfileDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface HomeApiService {
    @GET("users/profile")
    suspend fun getUserProfile(
        @Header("Authorization") headerCompanionValue: String
    ): Response<UserProfileDTO>

    @GET("artworks/user-favorites")
    suspend fun getUserFavoriteArtworks(
        @Header("Authorization") headerCompanionValue: String
    ): Response<List<ArtworkPreviewDTO>>
}