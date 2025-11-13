package com.project.musapp.feature.artwork.domain.usecase

import com.project.musapp.feature.artwork.domain.model.artwork.toUiModel
import com.project.musapp.feature.artwork.domain.repository.ArtworkRepository
import com.project.musapp.feature.artwork.presentation.model.artwork.ArtworkPreviewUiModel
import com.project.musapp.feature.auth.domain.usecase.GetUserTokenUseCase
import javax.inject.Inject

class GetSearchArtworksUseCase @Inject constructor(
    private val repository: ArtworkRepository,
    private val getUserTokenUseCase: GetUserTokenUseCase,
) {
    suspend operator fun invoke(): Result<List<ArtworkPreviewUiModel>> {
        return runCatching {
            repository.getSearchArtworks(
                userToken = getUserTokenUseCase().getOrThrow()
            ).map { artworkPreviewDomainModel ->
                artworkPreviewDomainModel.toUiModel()
            }
        }
    }
}