package com.project.musapp.feature.user.auth.registration.domain.usecase

import com.project.musapp.feature.user.auth.registration.domain.repository.UserRegistrationRepository
import javax.inject.Inject

class CreateUserUseCase @Inject constructor(private val repository: UserRegistrationRepository) {
    suspend operator fun invoke(email: String, password: String) =
        repository.createUser(email, password)
}