package com.project.musapp.feature.user.register.domain.useCase

import com.project.musapp.feature.user.register.domain.model.RegisterUserModel
import com.project.musapp.feature.user.register.domain.repository.UserRegisterRepository
import javax.inject.Inject

class UserRegisterUseCase @Inject constructor(private val userRepository: UserRegisterRepository) {
    suspend operator fun invoke(registerUserModel: RegisterUserModel) =
        userRepository.insertUser(registerUserModel)
}