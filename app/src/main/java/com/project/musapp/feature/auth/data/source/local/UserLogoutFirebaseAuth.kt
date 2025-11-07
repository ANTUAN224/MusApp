package com.project.musapp.feature.auth.data.source.local

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class UserLogoutFirebaseAuth @Inject constructor() {
    fun logOutUser() = Firebase.auth.signOut()
}