package com.project.musapp.feature.artwork.data.source.remote.api

import com.project.musapp.feature.artwork.data.model.dto.artwork.ArtworkDTO
import com.project.musapp.feature.artwork.data.model.dto.artwork.ArtworkPreviewDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ArtworkApiService {
    @GET("artworks/user")
    suspend fun getUserFavoriteArtworks(
        @Header("Authorization") headerCompanionValue: String
    ): Response<List<ArtworkPreviewDTO>>

    @GET("artworks/collection/{id}")
    suspend fun getCollectionArtworks(
        @Header("Authorization") headerCompanionValue: String,
        @Path("id") collectionId: Long
    ): Response<List<ArtworkPreviewDTO>>

    @GET("artworks/{id}")
    suspend fun getArtwork(
        @Header("Authorization") headerCompanionValue: String,
        @Path("id") artworkId: Long
    ): Response<ArtworkDTO>

    @GET("artworks")
    suspend fun getSearchArtworks(
        @Header("Authorization") headerCompanionValue: String
    ): Response<List<ArtworkPreviewDTO>>
}