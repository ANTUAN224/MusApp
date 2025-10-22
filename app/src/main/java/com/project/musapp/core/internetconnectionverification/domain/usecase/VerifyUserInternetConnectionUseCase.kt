package com.project.musapp.core.internetconnectionverification.domain.usecase

import com.project.musapp.core.internetconnectionverification.domain.repository.UserInternetConnectionVerificationRepository
import javax.inject.Inject

class VerifyUserInternetConnectionUseCase @Inject constructor(
    private val repository: UserInternetConnectionVerificationRepository
) {
    operator fun invoke() = repository.verifyUserInternetConnection()
}