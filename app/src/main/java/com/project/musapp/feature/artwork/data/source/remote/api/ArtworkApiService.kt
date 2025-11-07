package com.project.musapp.feature.artwork.data.source.remote.api

import com.project.musapp.feature.artwork.data.model.dto.artwork.ArtworkPreviewDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ArtworkApiService {
    @GET("artworks/user-favorites")
    suspend fun getUserFavoriteArtworks(
        @Header("Authorization") headerCompanionValue: String
    ): Response<List<ArtworkPreviewDTO>>
}