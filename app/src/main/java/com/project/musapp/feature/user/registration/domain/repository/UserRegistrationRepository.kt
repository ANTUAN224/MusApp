package com.project.musapp.feature.user.registration.domain.repository

import com.project.musapp.feature.user.registration.domain.model.UserRegistrationModel

interface UserRegisterRepository {
    suspend fun createFirebaseUser(email: String, password: String): Boolean

    suspend fun insertUser(
        userRegisterModel: UserRegistrationModel
    ): Boolean
}