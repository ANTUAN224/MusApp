package com.project.musapp.feature.collection.domain.usecase

import com.project.musapp.feature.auth.domain.usecase.GetUserTokenUseCase
import com.project.musapp.feature.collection.domain.model.toUiModel
import com.project.musapp.feature.collection.domain.repository.CollectionRepository
import com.project.musapp.feature.collection.presentation.model.CollectionReadingUiModel
import com.project.musapp.feature.collection.presentation.model.CollectionRenamingUiModel
import com.project.musapp.feature.collection.presentation.model.toDomainModel
import javax.inject.Inject

class RenameCollectionUseCase @Inject constructor(
    private val repository: CollectionRepository,
    private val getUserTokenUseCase: GetUserTokenUseCase
) {
    suspend operator fun invoke(
        collectionId: Long,
        collectionRenamingUiModel: CollectionRenamingUiModel
    ): Result<List<CollectionReadingUiModel>> {
        return runCatching {
            repository.renameCollection(
                userToken = getUserTokenUseCase().getOrThrow(),
                collectionId = collectionId,
                collectionRenamingDomainModel = collectionRenamingUiModel.toDomainModel()
            ).map { collectionReadingDomainModel -> collectionReadingDomainModel.toUiModel() }
        }
    }
}