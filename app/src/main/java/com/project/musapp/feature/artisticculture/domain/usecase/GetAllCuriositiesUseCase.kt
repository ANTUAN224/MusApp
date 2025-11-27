package com.project.musapp.feature.artisticculture.domain.usecase

import com.project.musapp.feature.artisticculture.domain.model.toUiModel
import com.project.musapp.feature.artisticculture.domain.repository.ArtisticCultureRepository
import com.project.musapp.feature.artisticculture.presentation.model.CuriosityUiModel
import com.project.musapp.feature.auth.domain.usecase.GetUserTokenUseCase
import javax.inject.Inject

class GetAllCuriositiesUseCase @Inject constructor(
    private val repository: ArtisticCultureRepository,
    private val getUserTokenUseCase: GetUserTokenUseCase
) {
    suspend operator fun invoke(): Result<List<CuriosityUiModel>> {
        return runCatching {
            repository.getAllCuriosities(
                userToken = getUserTokenUseCase().getOrThrow()
            ).map { curiosityDomainModel -> curiosityDomainModel.toUiModel() }
        }
    }
}