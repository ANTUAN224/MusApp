package com.project.musapp.feature.user.initialChecking.domain.usecase

import com.project.musapp.feature.user.initialChecking.domain.repository.UserInitialCheckingRepository
import javax.inject.Inject

class UserSessionVerificationUseCase @Inject constructor(private val repository: UserInitialCheckingRepository) {
    suspend operator fun invoke() =
        repository.verifyUserSession()
}