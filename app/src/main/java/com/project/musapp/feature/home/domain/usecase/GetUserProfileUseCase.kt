package com.project.musapp.feature.home.domain.usecase

import com.project.musapp.core.tokengetting.domain.usecase.GetUserTokenUseCase
import com.project.musapp.feature.home.domain.model.toUiModel
import com.project.musapp.feature.home.domain.repository.HomeRepository
import com.project.musapp.feature.home.presentation.model.UserProfileUiModel
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val repository: HomeRepository,
    private val getUserTokenUseCase: GetUserTokenUseCase
) {
    suspend operator fun invoke(): Result<UserProfileUiModel> {
        return runCatching {
            this.repository.getUserProfile(userToken = getUserTokenUseCase().getOrThrow())
                .toUiModel()
        }
    }
}