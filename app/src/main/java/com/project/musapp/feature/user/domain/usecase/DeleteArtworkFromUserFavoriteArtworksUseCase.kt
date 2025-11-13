package com.project.musapp.feature.user.domain.usecase

import com.project.musapp.feature.artwork.domain.model.artwork.toUiModel
import com.project.musapp.feature.artwork.presentation.model.artwork.ArtworkPreviewUiModel
import com.project.musapp.feature.auth.domain.usecase.GetUserTokenUseCase
import com.project.musapp.feature.user.domain.repository.UserRepository
import javax.inject.Inject

class DeleteArtworkFromUserFavoriteArtworksUseCase @Inject constructor(
    private val repository: UserRepository,
    private val getUserTokenUseCase: GetUserTokenUseCase
) {
    suspend operator fun invoke(artworkId: Long): Result<List<ArtworkPreviewUiModel>> {
        return runCatching {
            repository.deleteArtworkFromUserFavoriteArtworks(
                userToken = getUserTokenUseCase().getOrThrow(),
                artworkId = artworkId
            ).map { artworkPreviewDomainModel ->
                artworkPreviewDomainModel.toUiModel()
            }
        }
    }
}