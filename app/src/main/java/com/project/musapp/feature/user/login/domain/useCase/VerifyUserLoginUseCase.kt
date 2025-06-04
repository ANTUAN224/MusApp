package com.project.musapp.feature.user.login.domain.useCase

import com.project.musapp.feature.user.login.domain.repository.UserLoginRepository
import javax.inject.Inject

class VerifyUserLoginUseCase @Inject constructor(private val repository: UserLoginRepository) {
    suspend operator fun invoke(email: String, password: String) =
        repository.verifyUserLogin(email = email, password = password)
}