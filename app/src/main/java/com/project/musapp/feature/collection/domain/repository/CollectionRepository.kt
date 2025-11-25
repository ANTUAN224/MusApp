package com.project.musapp.feature.collection.domain.repository

import com.project.musapp.feature.artwork.domain.model.artwork.ArtworkPreviewDomainModel
import com.project.musapp.feature.collection.data.model.dto.CollectionBatchDTO
import com.project.musapp.feature.collection.domain.model.CollectionBatchDomainModel
import com.project.musapp.feature.collection.domain.model.CollectionCreationDomainModel
import com.project.musapp.feature.collection.domain.model.CollectionReadingDomainModel
import com.project.musapp.feature.collection.domain.model.CollectionRenamingDomainModel

interface CollectionRepository {
    suspend fun createCollection(
        userToken: String,
        collectionCreationDomainModel: CollectionCreationDomainModel
    ): List<CollectionReadingDomainModel>

    suspend fun renameCollection(
        userToken: String,
        collectionId: Long,
        collectionRenamingDomainModel: CollectionRenamingDomainModel
    ): List<CollectionReadingDomainModel>

    suspend fun deleteCollections(
        userToken: String,
        collectionBatchDomainModel: CollectionBatchDomainModel
    ): List<CollectionReadingDomainModel>

    suspend fun getUserCollections(userToken: String): List<CollectionReadingDomainModel>

    suspend fun getCollectionsWithThatArtwork(
        userToken: String,
        artworkId: Long
    ): List<CollectionReadingDomainModel>

    suspend fun getCollectionsWithoutThatArtwork(
        userToken: String,
        artworkId: Long
    ): List<CollectionReadingDomainModel>

    suspend fun addArtworkToCollections(
        userToken: String,
        artworkId: Long,
        collectionBatchDomainModel: CollectionBatchDomainModel
    )

    suspend fun deleteArtworkFromCollections(
        userToken: String,
        artworkId: Long,
        collectionBatchDomainModel: CollectionBatchDomainModel
    )
}