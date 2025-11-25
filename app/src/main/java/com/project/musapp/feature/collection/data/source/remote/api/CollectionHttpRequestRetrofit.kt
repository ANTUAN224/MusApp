package com.project.musapp.feature.collection.data.source.remote.api

import com.project.musapp.feature.collection.data.model.dto.CollectionBatchDTO
import com.project.musapp.feature.collection.data.model.dto.CollectionCreationDTO
import com.project.musapp.feature.collection.data.model.dto.CollectionRenamingDTO
import javax.inject.Inject

class CollectionHttpRequestRetrofit @Inject constructor(
    private val collectionApiService: CollectionApiService
) {
    suspend fun createCollection(
        userToken: String,
        collectionCreationDTO: CollectionCreationDTO
    )  =
        collectionApiService.createCollection(
            headerCompanionValue = "Bearer $userToken",
            collectionCreationDTO = collectionCreationDTO
        ).body()!!

    suspend fun renameCollection(
        userToken: String,
        collectionId: Long,
        collectionRenamingDTO: CollectionRenamingDTO
    ) =
        collectionApiService.renameCollection(
            headerCompanionValue = "Bearer $userToken",
            collectionId = collectionId,
            collectionRenamingDTO = collectionRenamingDTO
        ).body()!!

    suspend fun deleteCollections(
        userToken: String,
        collectionBatchDTO: CollectionBatchDTO
    ) =
        collectionApiService.deleteCollections(
            headerCompanionValue = "Bearer $userToken",
            collectionBatchDTO = collectionBatchDTO
        ).body()!!

    suspend fun getUserCollections(
        userToken: String
    ) =
        collectionApiService.getUserCollections(
            headerCompanionValue = "Bearer $userToken"
        ).body()!!

    suspend fun getCollectionsWithThatArtwork(
        userToken: String,
        artworkId: Long
    ) =
        collectionApiService.getCollectionsWithThatArtwork(
            headerCompanionValue = "Bearer $userToken",
            artworkId = artworkId
        ).body()!!

    suspend fun getCollectionsWithoutThatArtwork(
        userToken: String,
        artworkId: Long
    ) =
        collectionApiService.getCollectionsWithoutThatArtwork(
            headerCompanionValue = "Bearer $userToken",
            artworkId = artworkId
        ).body()!!

    suspend fun deleteArtworkFromCollections(
        userToken: String,
        artworkId: Long,
        collectionBatchDTO: CollectionBatchDTO
    ) =
        collectionApiService.deleteArtworkFromCollections(
            headerCompanionValue = "Bearer $userToken",
            artworkId = artworkId,
            collectionBatchDTO = collectionBatchDTO
        )

    suspend fun addArtworkToCollections(
        userToken: String,
        artworkId: Long,
        collectionBatchDTO: CollectionBatchDTO
    ) =
        collectionApiService.addArtworkToCollections(
            headerCompanionValue = "Bearer $userToken",
            artworkId = artworkId,
            collectionBatchDTO = collectionBatchDTO
        )
}