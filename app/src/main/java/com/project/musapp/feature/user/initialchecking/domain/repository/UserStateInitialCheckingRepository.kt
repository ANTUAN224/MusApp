package com.project.musapp.feature.user.initialchecking.domain.repository

interface UserStateInitialCheckingRepository {
    fun verifyUserConnection() : Boolean
    fun verifyUserSession() : Boolean
}