package com.project.musapp.feature.user.data.source.remote.api

import com.project.musapp.feature.artwork.data.model.dto.artwork.ArtworkPreviewDTO
import com.project.musapp.feature.user.data.model.dto.UserProfileDTO
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApiService {
    @GET("users/profile")
    suspend fun getUserProfile(
        @Header("Authorization") headerCompanionValue: String
    ): Response<UserProfileDTO>

    @PUT("users/favorite-artwork/{id}")
    suspend fun addArtworkToUserFavoriteArtworks(
        @Header("Authorization") headerCompanionValue: String,
        @Path("id") artworkId: Long
    ): Response<List<ArtworkPreviewDTO>>

    @DELETE("users/favorite-artwork/{id}")
    suspend fun deleteArtworkFromUserFavoriteArtworks(
        @Header("Authorization") headerCompanionValue: String,
        @Path("id") artworkId: Long
    ): Response<List<ArtworkPreviewDTO>>

    @GET("users/user-favorite-artworks")
    suspend fun getUserFavoriteArtworks(
        @Header("Authorization") headerCompanionValue: String
    ): Response<List<ArtworkPreviewDTO>>
}