package com.project.musapp.feature.user.auth.registration.domain.usecase

import com.project.musapp.core.tokengetting.domain.usecase.GetUserTokenUseCase
import com.project.musapp.feature.user.auth.registration.domain.repository.UserRegistrationRepository
import com.project.musapp.feature.user.auth.registration.presentation.model.UserRegistrationUiModel
import com.project.musapp.feature.user.auth.registration.presentation.model.toDomainModel
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(
    private val repository: UserRegistrationRepository,
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