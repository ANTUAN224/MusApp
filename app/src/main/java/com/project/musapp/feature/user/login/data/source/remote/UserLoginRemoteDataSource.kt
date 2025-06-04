package com.project.musapp.feature.user.login.data.source.remote

import com.project.musapp.feature.user.login.data.source.remote.client.UserLoginApiClient
import javax.inject.Inject

class UserLoginRemoteDataSource @Inject constructor(private val service: UserLoginApiClient) {
    suspend fun verifyUserLogin(email: String, password: String) = true
//        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
}