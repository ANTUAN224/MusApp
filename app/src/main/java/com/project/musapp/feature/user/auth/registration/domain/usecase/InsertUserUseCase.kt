package com.project.musapp.feature.user.auth.registration.domain.usecase

import com.project.musapp.feature.user.auth.registration.domain.model.UserRegistrationModel
import com.project.musapp.feature.user.auth.registration.domain.repository.UserRegistrationRepository
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val repository: UserRegistrationRepository) {
    suspend operator fun invoke(
        userToken: String,
        userRegistrationModel: UserRegistrationModel
    ) =
        repository.insertUser(
            userToken = userToken,
            userRegisterModel = userRegistrationModel
        )
}