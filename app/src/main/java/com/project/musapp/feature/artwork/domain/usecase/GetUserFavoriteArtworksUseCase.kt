package com.project.musapp.feature.artwork.domain.usecase

import com.project.musapp.feature.artwork.domain.model.artwork.toUiModel
import com.project.musapp.feature.artwork.domain.repository.ArtworkRepository
import com.project.musapp.feature.artwork.presentation.model.artwork.ArtworkPreviewUiModel
import com.project.musapp.feature.auth.domain.usecase.GetUserTokenUseCase
import javax.inject.Inject

class GetUserFavoriteArtworksUseCase @Inject constructor(
    private val repository: ArtworkRepository,
    private val getUserTokenUseCase: GetUserTokenUseCase,
    private val getArtworkImageUrlUseCase: GetArtworkImageUrlUseCase
) {
    suspend operator fun invoke(): Result<List<ArtworkPreviewUiModel>> {
        return runCatching {
            repository.getUserFavoriteArtworks(getUserTokenUseCase().getOrThrow())
                .map { artworkPreviewDomainModel ->
                    artworkPreviewDomainModel.toUiModel(
                        getArtworkImageUrlUseCase(
                            artworkPreviewDomainModel.imagePathText
                        ).getOrThrow()
                    )
                }
        }
    }
}