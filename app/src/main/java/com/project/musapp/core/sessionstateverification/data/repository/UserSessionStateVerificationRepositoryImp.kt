package com.project.musapp.core.sessionstateverification.data.repository

import com.project.musapp.core.sessionstateverification.data.source.local.UserSessionStateVerificationFirebaseAuth
import com.project.musapp.core.sessionstateverification.domain.repository.UserSessionStateVerificationRepository
import javax.inject.Inject

class UserSessionStateVerificationRepositoryImp @Inject constructor(
    private val userSessionStateVerificationFirebaseAuth: UserSessionStateVerificationFirebaseAuth
) :
    UserSessionStateVerificationRepository {
    override fun verifyUserSession() =
        userSessionStateVerificationFirebaseAuth.verifyUserSession()
}