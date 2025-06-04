package com.project.musapp.feature.user.initialChecking.domain.repository

interface UserInitialCheckingRepository {
    suspend fun verifyUserConnection() : Boolean
    suspend fun verifyUserSession() : Boolean
}