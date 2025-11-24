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
}