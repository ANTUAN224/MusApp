package com.project.musapp.feature.user.auth.login.data.source.remote

import android.util.Log
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.musapp.core.internetconnectionverification.domain.exception.NoInternetConnectionException
import com.project.musapp.feature.user.auth.login.domain.exception.UserNotFoundException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class UserLoginFirebaseAuth @Inject constructor() {
    suspend fun verifyUserLogin(email: String, password: String) {
        val timeout = 5.seconds

        try {
            withTimeout(timeout = timeout) {
                Log.d("NETWORK_EXCEPTION", "Verificando credenciales del usuario...")
                Firebase.auth.signInWithEmailAndPassword(email, password).await()
                Log.d("NETWORK_EXCEPTION", "Credenciales del usuario verificadas.")
            }
        } catch (e: Exception) {
            when (e) {
                is FirebaseNetworkException, is TimeoutCancellationException -> throw NoInternetConnectionException()
                is FirebaseAuthException -> throw UserNotFoundException()
            }
        }
    }
}