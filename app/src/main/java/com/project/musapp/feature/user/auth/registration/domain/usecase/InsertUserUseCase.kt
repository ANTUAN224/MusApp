package com.project.musapp.feature.user.auth.registration.domain.usecase

import com.project.musapp.feature.user.auth.registration.domain.repository.UserRegistrationRepository
import com.project.musapp.feature.user.auth.registration.presentation.model.UserRegistrationPresentationModel
import com.project.musapp.feature.user.auth.registration.presentation.model.toDomainModel
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val repository: UserRegistrationRepository) {
    suspend operator fun invoke(
        userToken: String,
        userRegistrationPresentationModel: UserRegistrationPresentationModel
    ): Result<Unit> {
        return runCatching {
            repository.insertUser(
                userToken = userToken,
                userRegistrationDomainModel = userRegistrationPresentationModel.toDomainModel()
            )
        }
    }
}