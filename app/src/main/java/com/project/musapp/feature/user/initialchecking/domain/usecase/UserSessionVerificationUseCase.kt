package com.project.musapp.feature.user.initialchecking.domain.usecase

import com.project.musapp.feature.user.initialchecking.domain.repository.UserInitialCheckingRepository
import javax.inject.Inject

class UserSessionVerificationUseCase @Inject constructor(private val repository: UserInitialCheckingRepository) {
    operator fun invoke() =
        repository.verifyUserSession()
}