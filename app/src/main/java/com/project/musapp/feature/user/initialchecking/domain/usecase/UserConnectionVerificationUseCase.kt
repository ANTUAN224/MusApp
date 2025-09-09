package com.project.musapp.feature.user.initialChecking.domain.usecase

import com.project.musapp.feature.user.initialChecking.domain.repository.UserInitialCheckingRepository
import javax.inject.Inject

class UserConnectionVerificationUseCase @Inject constructor(private val repository: UserInitialCheckingRepository) {
    operator fun invoke() =
        repository.verifyUserConnection()
}