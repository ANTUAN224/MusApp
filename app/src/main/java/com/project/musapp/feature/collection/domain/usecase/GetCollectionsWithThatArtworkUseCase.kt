package com.project.musapp.feature.collection.domain.usecase

import com.project.musapp.feature.auth.domain.usecase.GetUserTokenUseCase
import com.project.musapp.feature.collection.domain.model.toUiModel
import com.project.musapp.feature.collection.domain.repository.CollectionRepository
import com.project.musapp.feature.collection.presentation.model.CollectionReadingUiModel
import javax.inject.Inject

class GetCollectionsWithThatArtworkUseCase @Inject constructor(
    private val repository: CollectionRepository,
    private val getUserTokenUseCase: GetUserTokenUseCase
) {
    suspend operator fun invoke(artworkId: Long): Result<List<CollectionReadingUiModel>> {
        return runCatching {
            repository.getCollectionsWithThatArtwork(
                userToken = getUserTokenUseCase().getOrThrow(),
                artworkId = artworkId
            ).map { collectionReadingDomainModel -> collectionReadingDomainModel.toUiModel() }
        }
    }
}