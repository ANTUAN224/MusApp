package com.project.musapp.feature.user.auth.registration.data.source.remote

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.musapp.core.internetconnectionverification.domain.exception.NoInternetConnectionException
import com.project.musapp.feature.user.auth.registration.domain.exception.EmailAlreadyInUseException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRegistrationFirebaseAuth @Inject constructor() {
    suspend fun createUser(email: String, password: String) {
        try {
            Firebase.auth.createUserWithEmailAndPassword(email, password).await()
        } catch (_: FirebaseNetworkException) {
            throw NoInternetConnectionException()
        } catch (_: FirebaseAuthUserCollisionException) {
            throw EmailAlreadyInUseException()
        }
    }
}