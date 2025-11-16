package com.project.musapp.feature.auth.data.source.local

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class UserSessionStateVerificationFirebaseAuth @Inject constructor() {
    fun verifyUserSession(): Boolean {
        Log.d("EJECUCIÓN", "Usuario actual (inicialmente) -> ${Firebase.auth.currentUser}")
        var isUserLoggedIn = false

        Firebase.auth.addAuthStateListener { firebaseAuth ->
            isUserLoggedIn = firebaseAuth.currentUser != null
            Log.d("EJECUCIÓN", "Usuario actual (finalmente) -> ${Firebase.auth.currentUser}")
        }

        return isUserLoggedIn
    }
}