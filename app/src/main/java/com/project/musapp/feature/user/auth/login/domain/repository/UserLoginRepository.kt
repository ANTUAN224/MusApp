package com.project.musapp.feature.user.auth.login.domain.repository

interface UserLoginRepository {
    suspend fun verifyUserLogin(email: String, password: String): Boolean
}