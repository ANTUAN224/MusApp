package com.project.musapp.feature.artwork.domain.usecase

import com.project.musapp.feature.artwork.domain.model.artwork.toUiModel
import com.project.musapp.feature.artwork.domain.repository.ArtworkRepository
import com.project.musapp.feature.artwork.presentation.model.artwork.ArtworkUiModel
import com.project.musapp.feature.auth.domain.usecase.GetUserTokenUseCase
import javax.inject.Inject

class GetArtworkUseCase @Inject constructor(
    private val repository: ArtworkRepository,
    private val getUserTokenUseCase: GetUserTokenUseCase
) {
    suspend operator fun invoke(artworkId: Long): Result<ArtworkUiModel> {
        return runCatching {
            repository.getArtwork(
                userToken = getUserTokenUseCase().getOrThrow(),
                artworkId = artworkId
            ).toUiModel()
        }
    }
}