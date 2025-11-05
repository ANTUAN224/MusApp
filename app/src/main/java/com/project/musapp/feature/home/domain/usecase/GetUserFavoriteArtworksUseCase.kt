package com.project.musapp.feature.home.domain.usecase

import com.project.musapp.core.tokengetting.domain.usecase.GetUserTokenUseCase
import com.project.musapp.feature.home.domain.model.toUiModel
import com.project.musapp.feature.home.domain.repository.HomeRepository
import com.project.musapp.feature.home.presentation.model.ArtworkPreviewUiModel
import javax.inject.Inject

class GetUserFavoriteArtworksUseCase @Inject constructor(
    private val repository: HomeRepository,
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