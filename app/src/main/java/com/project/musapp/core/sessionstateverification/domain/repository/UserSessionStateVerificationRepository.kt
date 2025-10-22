package com.project.musapp.core.sessionstateverification.domain.repository

interface UserSessionStateVerificationRepository {
    fun verifyUserSession() : Boolean
}