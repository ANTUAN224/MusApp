package com.project.musapp.feature.user.domain.usecase

import com.project.musapp.feature.auth.domain.usecase.GetUserTokenUseCase
import com.project.musapp.feature.user.domain.model.toUiModel
import com.project.musapp.feature.user.domain.repository.UserRepository
import com.project.musapp.feature.user.presentation.model.UserProfileUiModel
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(
    private val repository: UserRepository,
    private val getUserTokenUseCase: GetUserTokenUseCase
) {
    suspend operator fun invoke(): Result<UserProfileUiModel> {
        return runCatching {
            this.repository.getUserProfile(userToken = getUserTokenUseCase().getOrThrow())
                .toUiModel()
        }
    }
}