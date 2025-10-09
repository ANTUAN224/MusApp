package com.project.musapp.feature.user.initialchecking.domain.usecase

import com.project.musapp.feature.user.initialchecking.domain.repository.UserStateInitialCheckingRepository
import javax.inject.Inject

class UserConnectionVerificationUseCase @Inject constructor(private val repository: UserStateInitialCheckingRepository) {
    operator fun invoke() =
        repository.verifyUserConnection()
}