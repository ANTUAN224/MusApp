package com.project.musapp.feature.user.registration.domain.usecase

import com.project.musapp.feature.user.registration.domain.repository.UserRegisterRepository
import javax.inject.Inject

class CreateFirebaseUserUseCase @Inject constructor(private val userRegisterRepository: UserRegisterRepository) {
    suspend operator fun invoke(email: String, password: String) =
        userRegisterRepository.createFirebaseUser(email, password)
}