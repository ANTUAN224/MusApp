package com.project.musapp.feature.home.user.register.domain.useCase

import com.project.musapp.feature.home.user.register.domain.model.RegisterUserModel
import com.project.musapp.feature.home.user.register.domain.repository.UserRegisterRepository

class UserRegisterUseCase(private val userRepository: UserRegisterRepository) {
    suspend operator fun invoke (registerUserModel: RegisterUserModel) =
        userRepository.insertUser(registerUserModel)
}