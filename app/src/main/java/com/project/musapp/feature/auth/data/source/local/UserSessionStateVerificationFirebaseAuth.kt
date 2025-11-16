package com.project.musapp.feature.auth.data.source.local

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class UserSessionStateVerificationFirebaseAuth @Inject constructor() {
    fun verifyUserSession(): Flow<FirebaseUser?> {
        return callbackFlow {
            val authStateListener = FirebaseAuth.AuthStateListener {
                trySend(Firebase.auth.currentUser)
            }

            Firebase.auth.addAuthStateListener(authStateListener)

            awaitClose { Firebase.auth.removeAuthStateListener(authStateListener) }
        }
    }
}