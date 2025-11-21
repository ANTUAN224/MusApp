package com.project.musapp.feature.collection.domain.repository

import com.project.musapp.feature.artwork.domain.model.artwork.ArtworkPreviewDomainModel
import com.project.musapp.feature.collection.domain.model.CollectionBatchDeletionDomainModel
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
        collectionBatchDeletionDomainModel: CollectionBatchDeletionDomainModel
    ): List<CollectionReadingDomainModel>

    suspend fun getUserCollections(userToken: String): List<CollectionReadingDomainModel>

    suspend fun getCollectionArtworks(
        userToken: String,
        collectionId: Long
    ): List<ArtworkPreviewDomainModel>

    suspend fun addArtworkToCollections(
        userToken: String,
        artworkId: Long,
        collectionIds: List<Long>
    ): List<ArtworkPreviewDomainModel>

    suspend fun deleteArtworkFromCollections(
        userToken: String,
        artworkId: Long,
        collectionIds: List<Long>
    ): List<ArtworkPreviewDomainModel>
}