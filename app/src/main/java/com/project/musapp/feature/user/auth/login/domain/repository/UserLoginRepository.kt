package com.project.musapp.feature.user.auth.login.domain.repository

interface UserLoginRepository {
    suspend fun logInUser(email: String, password: String)
}