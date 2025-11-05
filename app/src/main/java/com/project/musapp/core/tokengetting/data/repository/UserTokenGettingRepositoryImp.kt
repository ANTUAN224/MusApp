package com.project.musapp.core.tokengetting.data.repository

import com.google.firebase.FirebaseNetworkException
import com.project.musapp.core.internetconnectionverification.domain.exception.InternetConnectionVerificationException
import com.project.musapp.core.tokengetting.data.source.remote.UserTokenGettingFirebaseAuth
import com.project.musapp.core.tokengetting.domain.repository.UserTokenGettingRepository
import javax.inject.Inject

class UserTokenGettingRepositoryImp @Inject constructor(
    private val userTokenGettingFirebaseAuth: UserTokenGettingFirebaseAuth
) : UserTokenGettingRepository {
    override suspend fun getUserToken(): String {
        try {
            return this.userTokenGettingFirebaseAuth.getFirebaseUserToken()
        } catch (_: FirebaseNetworkException) {
            throw InternetConnectionVerificationException.NoInternetConnectionException
        }
    }
}