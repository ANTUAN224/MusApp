package com.project.musapp.feature.collection.data.repository

import com.project.musapp.core.network.domain.exception.NetworkException
import com.project.musapp.feature.artwork.domain.model.artwork.ArtworkPreviewDomainModel
import com.project.musapp.feature.collection.data.model.dto.toDomainModel
import com.project.musapp.feature.collection.data.source.remote.api.CollectionHttpRequestRetrofit
import com.project.musapp.feature.collection.domain.model.CollectionBatchDomainModel
import com.project.musapp.feature.collection.domain.model.CollectionCreationDomainModel
import com.project.musapp.feature.collection.domain.model.CollectionReadingDomainModel
import com.project.musapp.feature.collection.domain.model.CollectionRenamingDomainModel
import com.project.musapp.feature.collection.domain.model.toDTO
import com.project.musapp.feature.collection.domain.repository.CollectionRepository
import java.io.IOException
import javax.inject.Inject

class CollectionRepositoryImp @Inject constructor(
    private val collectionHttpRequestRetrofit: CollectionHttpRequestRetrofit
) : CollectionRepository {
    override suspend fun createCollection(
        userToken: String,
        collectionCreationDomainModel: CollectionCreationDomainModel
    ): List<CollectionReadingDomainModel> {
        try {
            return collectionHttpRequestRetrofit.createCollection(
                userToken = userToken,
                collectionCreationDTO = collectionCreationDomainModel.toDTO()
            ).map { collectionReadingDTO -> collectionReadingDTO.toDomainModel() }
        } catch (_: IOException) {
            throw NetworkException.NoInternetConnectionException
        }
    }

    override suspend fun renameCollection(
        userToken: String,
        collectionId: Long,
        collectionRenamingDomainModel: CollectionRenamingDomainModel
    ): List<CollectionReadingDomainModel> {
        try {
            return collectionHttpRequestRetrofit.renameCollection(
                userToken = userToken,
                collectionId = collectionId,
                collectionRenamingDTO = collectionRenamingDomainModel.toDTO()
            ).map { collectionReadingDTO -> collectionReadingDTO.toDomainModel() }
        } catch (_: IOException) {
            throw NetworkException.NoInternetConnectionException
        }
    }

    override suspend fun deleteCollections(
        userToken: String,
        collectionBatchDomainModel: CollectionBatchDomainModel
    ): List<CollectionReadingDomainModel> {
        try {
            return collectionHttpRequestRetrofit.deleteCollections(
                userToken = userToken,
                collectionBatchDTO = collectionBatchDomainModel.toDTO()
            ).map { collectionReadingDTO -> collectionReadingDTO.toDomainModel() }
        } catch (_: IOException) {
            throw NetworkException.NoInternetConnectionException
        }
    }

    override suspend fun getUserCollections(userToken: String): List<CollectionReadingDomainModel> {
        try {
            return collectionHttpRequestRetrofit.getUserCollections(
                userToken = userToken
            ).map { collectionReadingDTO -> collectionReadingDTO.toDomainModel() }
        } catch (_: IOException) {
            throw NetworkException.NoInternetConnectionException
        }
    }

    override suspend fun addArtworkToCollections(
        userToken: String,
        artworkId: Long,
        collectionIds: List<Long>
    ): List<ArtworkPreviewDomainModel> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteArtworkFromCollections(
        userToken: String,
        artworkId: Long,
        collectionIds: List<Long>
    ): List<ArtworkPreviewDomainModel> {
        TODO("Not yet implemented")
    }
}