package com.project.musapp.feature.auth.domain.usecase

import com.project.musapp.feature.auth.domain.repository.UserAuthRepository
import javax.inject.Inject

class LogInUserUseCase @Inject constructor(private val repository: UserAuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return runCatching { repository.logInUser(email = email, password = password) }
    }
}