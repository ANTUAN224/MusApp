package com.project.musapp.feature.user.initialchecking.domain.repository

interface UserInitialCheckingRepository {
    fun verifyUserConnection() : Boolean
    fun verifyUserSession() : Boolean
}