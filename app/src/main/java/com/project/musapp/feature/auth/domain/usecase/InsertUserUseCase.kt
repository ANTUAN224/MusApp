package com.project.musapp.feature.auth.domain.usecase

import com.project.musapp.feature.auth.domain.repository.UserAuthRepository
import com.project.musapp.feature.auth.presentation.registration.model.UserRegistrationUiModel
import com.project.musapp.feature.auth.presentation.registration.model.toDomainModel
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val repository: UserAuthRepository,
    private val getUserTokenUseCase: GetUserTokenUseCase
) {
    suspend operator fun invoke(
        userRegistrationUiModel: UserRegistrationUiModel
    ): Result<Unit> {
        return runCatching {
            repository.insertUser(
                userToken = getUserTokenUseCase().getOrThrow(),
                userRegistrationDomainModel = userRegistrationUiModel.toDomainModel()
            )
        }
    }
}