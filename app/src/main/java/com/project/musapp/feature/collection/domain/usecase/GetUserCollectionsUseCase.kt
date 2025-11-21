package com.project.musapp.feature.collection.domain.usecase

import com.project.musapp.feature.auth.domain.usecase.GetUserTokenUseCase
import com.project.musapp.feature.collection.domain.model.toUiModel
import com.project.musapp.feature.collection.domain.repository.CollectionRepository
import com.project.musapp.feature.collection.presentation.model.CollectionReadingUiModel
import com.project.musapp.feature.user.domain.repository.UserRepository
import javax.inject.Inject

class GetUserCollectionsUseCase @Inject constructor(
    private val repository: CollectionRepository,
    private val getUserTokenUseCase: GetUserTokenUseCase
) {
    suspend operator fun invoke(): Result<List<CollectionReadingUiModel>> {
        return runCatching {
            repository.getUserCollections(
                userToken = getUserTokenUseCase().getOrThrow()
            ).map { collectionReadingDomainModel -> collectionReadingDomainModel.toUiModel() }
        }
    }
}