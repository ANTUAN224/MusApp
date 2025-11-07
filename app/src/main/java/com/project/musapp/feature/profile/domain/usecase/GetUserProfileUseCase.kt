package com.project.musapp.feature.profile.domain.usecase

import com.project.musapp.feature.auth.domain.usecase.GetUserTokenUseCase
import com.project.musapp.feature.profile.domain.model.toUiModel
import com.project.musapp.feature.profile.domain.repository.UserProfileRepository
import com.project.musapp.feature.profile.presentation.model.UserProfileUiModel
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val repository: UserProfileRepository,
    private val getUserTokenUseCase: GetUserTokenUseCase
) {
    suspend operator fun invoke(): Result<UserProfileUiModel> {
        return runCatching {
            this.repository.getUserProfile(userToken = getUserTokenUseCase().getOrThrow())
                .toUiModel()
        }
    }
}