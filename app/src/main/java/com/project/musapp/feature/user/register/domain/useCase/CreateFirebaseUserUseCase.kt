package com.project.musapp.feature.user.register.domain.useCase

import com.project.musapp.feature.user.register.domain.repository.UserRegisterRepository
import javax.inject.Inject

class CreateFirebaseUserUseCase @Inject constructor(private val userRegisterRepository: UserRegisterRepository) {
    suspend operator fun invoke(email: String, password: String) =
        userRegisterRepository.createFirebaseUser(email, password)
}