package com.project.musapp.feature.user.login.domain.repository

interface UserLoginRepository {
    suspend fun verifyUserLogin(email: String, password: String): Boolean
}