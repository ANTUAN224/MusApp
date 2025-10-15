package com.project.musapp.feature.user.registration.domain.usecase

import com.project.musapp.feature.user.registration.domain.model.UserRegistrationModel
import com.project.musapp.feature.user.registration.domain.repository.UserRegistrationRepository
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val userRepository: UserRegistrationRepository) {
    suspend operator fun invoke(userRegistrationModel: UserRegistrationModel) =
        userRepository.insertUser(userRegistrationModel)
}