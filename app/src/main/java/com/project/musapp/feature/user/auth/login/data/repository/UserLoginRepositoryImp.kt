package com.project.musapp.feature.user.auth.login.data.repository

import com.project.musapp.feature.user.auth.login.data.source.remote.UserLoginFirebaseAuth
import com.project.musapp.feature.user.auth.login.domain.repository.UserLoginRepository
import javax.inject.Inject

class UserLoginRepositoryImp @Inject constructor(private val userLoginFirebaseAuth: UserLoginFirebaseAuth) :
    UserLoginRepository {
    override suspend fun logInUser(email: String, password: String) =
        userLoginFirebaseAuth.logInUser(email = email, password = password)
}