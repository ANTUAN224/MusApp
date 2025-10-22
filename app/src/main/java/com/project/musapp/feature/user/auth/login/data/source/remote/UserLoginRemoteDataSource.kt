package com.project.musapp.feature.user.auth.login.data.source.remote

import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.project.musapp.feature.user.auth.login.data.source.remote.client.UserLoginApiClient
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserLoginRemoteDataSource @Inject constructor(private val service: UserLoginApiClient) {
    suspend fun verifyUserLogin(email: String, password: String) =
        try {
            val authResult =
                Firebase.auth.signInWithEmailAndPassword(email, password).await()

            authResult.user != null
        } catch (_: FirebaseAuthException) {
            false
        } catch (_: Exception) {
            false
        }
}