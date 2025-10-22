package com.project.musapp.core.tokengetting.data.source.remote

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.musapp.core.internetconnectionverification.domain.exception.NoInternetConnectionException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserTokenGettingFirebaseAuth @Inject constructor() {
    suspend fun getFirebaseUserToken(): String {
        try {
            val firebaseUserTokenResult =
                Firebase.auth.currentUser!!.getIdToken(true).await()

            return firebaseUserTokenResult.token!!
        } catch (_: FirebaseNetworkException) {
            throw NoInternetConnectionException()
        }
    }
}