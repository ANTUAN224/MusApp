package com.project.musapp.feature.user.registration.domain.usecase

import com.project.musapp.feature.user.registration.domain.model.UserRegistrationModel
import com.project.musapp.feature.user.registration.domain.repository.UserRegisterRepository
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val userRepository: UserRegisterRepository) {
    suspend operator fun invoke(userRegistrationModel: UserRegistrationModel) =
        userRepository.insertUser(userRegistrationModel)
}