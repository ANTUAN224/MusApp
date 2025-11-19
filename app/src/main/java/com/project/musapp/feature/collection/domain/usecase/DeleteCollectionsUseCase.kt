package com.project.musapp.feature.collection.domain.usecase

import com.project.musapp.feature.auth.domain.usecase.GetUserTokenUseCase
import com.project.musapp.feature.collection.domain.model.toUiModel
import com.project.musapp.feature.collection.domain.repository.CollectionRepository
import com.project.musapp.feature.collection.presentation.model.CollectionBatchDeletionUiModel
import com.project.musapp.feature.collection.presentation.model.CollectionReadingUiModel
import com.project.musapp.feature.collection.presentation.model.toDomainModel
import javax.inject.Inject

class DeleteCollectionsUseCase @Inject constructor(
    private val repository: CollectionRepository,
    private val getUserTokenUseCase: GetUserTokenUseCase
) {
    suspend operator fun invoke(collectionBatchDeletionUiModel: CollectionBatchDeletionUiModel): Result<List<CollectionReadingUiModel>> {
        return runCatching {
            repository.deleteCollections(
                userToken = getUserTokenUseCase().getOrThrow(),
                collectionBatchDeletionDomainModel = collectionBatchDeletionUiModel.toDomainModel()
            ).map { collectionReadingDomainModel -> collectionReadingDomainModel.toUiModel() }
        }
    }
}