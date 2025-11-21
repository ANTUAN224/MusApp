package com.project.musapp.feature.collection.data.source.remote.api

import com.project.musapp.feature.collection.data.model.dto.CollectionBatchDeletionDTO
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
        collectionBatchDeletionDTO: CollectionBatchDeletionDTO
    ) =
        collectionApiService.deleteCollections(
            headerCompanionValue = "Bearer $userToken",
            collectionBatchDeletionDTO = collectionBatchDeletionDTO
        ).body()!!

    suspend fun getUserCollections(
        userToken: String
    ) =
        collectionApiService.getUserCollections(
            headerCompanionValue = "Bearer $userToken"
        ).body()!!

    suspend fun getCollectionArtworks(
        userToken: String,
        collectionId: Long
    ) =
        collectionApiService.getCollectionArtworks(
            headerCompanionValue = "Bearer $userToken",
            collectionId = collectionId
        ).body()!!
}