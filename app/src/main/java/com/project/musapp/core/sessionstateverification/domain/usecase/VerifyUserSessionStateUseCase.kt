package com.project.musapp.core.sessionstateverification.domain.usecase

import com.project.musapp.core.sessionstateverification.domain.repository.UserSessionStateVerificationRepository
import javax.inject.Inject

class VerifyUserSessionStateUseCase @Inject constructor(private val repository: UserSessionStateVerificationRepository) {
    operator fun invoke() = repository.verifyUserSession()
}