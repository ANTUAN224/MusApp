package com.project.musapp.feature.artisticculture.domain.usecase

import com.project.musapp.feature.artisticculture.domain.model.toUiModel
import com.project.musapp.feature.artisticculture.domain.repository.ArtisticCultureRepository
import com.project.musapp.feature.artisticculture.presentation.model.TermUiModel
import com.project.musapp.feature.auth.domain.usecase.GetUserTokenUseCase
import javax.inject.Inject

class GetAllTermsUseCase @Inject constructor(
    private val repository: ArtisticCultureRepository,
    private val getUserTokenUseCase: GetUserTokenUseCase
) {
    suspend operator fun invoke(): Result<List<TermUiModel>> {
        return runCatching {
            repository.getAllTerms(
                userToken = getUserTokenUseCase().getOrThrow()
            ).map { termDomainModel -> termDomainModel.toUiModel() }
        }
    }
}