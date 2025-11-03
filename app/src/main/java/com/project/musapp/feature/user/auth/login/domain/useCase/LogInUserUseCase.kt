package com.project.musapp.feature.user.auth.login.domain.useCase

import com.project.musapp.feature.user.auth.login.domain.repository.UserLoginRepository
import javax.inject.Inject

class LogInUserUseCase @Inject constructor(private val repository: UserLoginRepository) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return runCatching { repository.logInUser(email = email, password = password) }
    }
}