package com.project.musapp.feature.collection.domain.usecase

import com.project.musapp.feature.auth.domain.usecase.GetUserTokenUseCase
import com.project.musapp.feature.collection.domain.repository.CollectionRepository
import com.project.musapp.feature.collection.presentation.model.CollectionBatchUiModel
import com.project.musapp.feature.collection.presentation.model.toDomainModel
import javax.inject.Inject

class DeleteArtworkFromCollectionsUseCase @Inject constructor(
    private val repository: CollectionRepository,
    private val getUserTokenUseCase: GetUserTokenUseCase
) {
    suspend operator fun invoke(
        artworkId: Long,
        collectionBatchUiModel: CollectionBatchUiModel
    ): Result<Unit> {
        return runCatching {
            repository.deleteArtworkFromCollections(
                userToken = getUserTokenUseCase().getOrThrow(),
                artworkId = artworkId,
                collectionBatchDomainModel = collectionBatchUiModel.toDomainModel()
            )
        }
    }
}