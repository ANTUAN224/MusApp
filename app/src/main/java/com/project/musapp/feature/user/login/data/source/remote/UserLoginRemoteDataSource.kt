package com.project.musapp.feature.user.login.data.source.remote

import com.google.firebase.auth.FirebaseAuth
import com.project.musapp.feature.user.login.data.source.remote.service.LoginUserApiService
import javax.inject.Inject

class UserLoginRemoteDataSource @Inject constructor(private val service: LoginUserApiService) {
    suspend fun verifyUserLogin(email: String, password: String) = true
//        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
}