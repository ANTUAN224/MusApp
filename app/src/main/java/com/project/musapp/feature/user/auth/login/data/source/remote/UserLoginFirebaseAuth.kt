package com.project.musapp.feature.user.auth.login.data.source.remote

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

class UserLoginFirebaseAuth @Inject constructor() {
    suspend fun logInUser(email: String, password: String) {
        val timeout = 5.seconds

        withTimeout(timeout = timeout) {
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
        }
    }
}