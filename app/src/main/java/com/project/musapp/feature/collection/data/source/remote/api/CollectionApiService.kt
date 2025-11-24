package com.project.musapp.feature.collection.data.source.remote.api

import com.project.musapp.feature.artwork.data.model.dto.artwork.ArtworkPreviewDTO
import com.project.musapp.feature.collection.data.model.dto.CollectionBatchDTO
import com.project.musapp.feature.collection.data.model.dto.CollectionCreationDTO
import com.project.musapp.feature.collection.data.model.dto.CollectionReadingDTO
import com.project.musapp.feature.collection.data.model.dto.CollectionRenamingDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CollectionApiService {
    @POST("collections")
    suspend fun createCollection(
        @Header("Authorization") headerCompanionValue: String,
        @Body collectionCreationDTO: CollectionCreationDTO
    ): Response<List<CollectionReadingDTO>>

    @POST("collections/batch-delete")
    suspend fun deleteCollections(
        @Header("Authorization") headerCompanionValue: String,
        @Body collectionBatchDTO: CollectionBatchDTO
    ): Response<List<CollectionReadingDTO>>

    @GET("collections/user")
    suspend fun getUserCollections(
        @Header("Authorization") headerCompanionValue: String
    ): Response<List<CollectionReadingDTO>>

    @PUT("collections/{id}")
    suspend fun renameCollection(
        @Header("Authorization") headerCompanionValue: String,
        @Path("id") collectionId: Long,
        @Body collectionRenamingDTO: CollectionRenamingDTO
    ): Response<List<CollectionReadingDTO>>

    @GET("collections/{id}/artworks")
    suspend fun getCollectionArtworks(
        @Header("Authorization") headerCompanionValue: String,
        @Path("id") collectionId: Long
    ): Response<List<ArtworkPreviewDTO>>
}