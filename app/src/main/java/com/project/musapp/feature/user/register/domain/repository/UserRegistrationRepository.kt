package com.project.musapp.feature.user.register.domain.repository

import com.project.musapp.feature.user.register.domain.model.RegisterUserModel

interface UserRegisterRepository {
    suspend fun createFirebaseUser(email: String, password: String): Boolean

    suspend fun insertUser(
        userRegisterModel: RegisterUserModel
    ): Boolean
}