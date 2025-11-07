package com.project.musapp.core.network.domain.usecase

import com.project.musapp.core.network.domain.repository.UserInternetConnectionVerificationRepository
import javax.inject.Inject

class VerifyUserInternetConnectionUseCase @Inject constructor(
    private val repository: UserInternetConnectionVerificationRepository
) {
    operator fun invoke(): Result<Unit> {
        return runCatching { this.repository.verifyUserInternetConnection() }
    }
}