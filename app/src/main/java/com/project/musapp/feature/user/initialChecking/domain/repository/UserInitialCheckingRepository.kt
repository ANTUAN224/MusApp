package com.project.musapp.feature.user.initialChecking.domain.repository

interface UserInitialCheckingRepository {
    fun verifyUserConnection() : Boolean
    fun verifyUserSession() : Boolean
}