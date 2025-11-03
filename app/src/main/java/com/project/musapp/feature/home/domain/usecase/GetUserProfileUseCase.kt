package com.project.musapp.feature.home.domain.usecase

import com.project.musapp.feature.home.domain.model.toPresentationModel
import com.project.musapp.feature.home.domain.repository.HomeRepository
import com.project.musapp.feature.home.presentation.model.UserProfilePresentationModel
import javax.inject.Inject

class GetUserProfileUseCase @Inject constructor(private val repository: HomeRepository) {
    suspend operator fun invoke(userToken: String): Result<UserProfilePresentationModel> {
        return runCatching {
            this.repository.getUserProfile(userToken = userToken).toPresentationModel()
        }
    }
}