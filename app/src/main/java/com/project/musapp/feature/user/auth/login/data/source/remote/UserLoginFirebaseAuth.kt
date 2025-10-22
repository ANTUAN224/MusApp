package com.project.musapp.feature.user.auth.login.data.source.remote

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.musapp.core.internetconnectionverification.domain.exception.NoInternetConnectionException
import com.project.musapp.feature.user.auth.login.domain.exception.UserNotFoundException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserLoginFirebaseAuth @Inject constructor() {
    suspend fun verifyUserLogin(email: String, password: String) {
        try {
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
        } catch (_: FirebaseNetworkException) {
            throw NoInternetConnectionException()
        } catch (_: FirebaseAuthException) {
            throw UserNotFoundException()
        }
    }
}