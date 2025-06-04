package com.project.musapp.feature.user.register.domain.repository

import com.project.musapp.feature.user.register.domain.model.UserRegisterModel

interface UserRegisterRepository {
    suspend fun createToken(email: String, password: String): Boolean

    suspend fun insertUser(
        userRegisterModel: UserRegisterModel
    ): Boolean
}