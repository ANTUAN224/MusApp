package com.project.musapp.feature.user.registration.domain.usecase

import com.project.musapp.feature.user.registration.domain.repository.UserRegistrationRepository
import javax.inject.Inject

class CreateFirebaseUserUseCase @Inject constructor(private val userRegistrationRepository: UserRegistrationRepository) {
    suspend operator fun invoke(email: String, password: String) =
        userRegistrationRepository.createFirebaseUser(email, password)
}