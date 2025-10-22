package com.project.musapp.feature.user.auth.login.data.repository

import com.project.musapp.feature.user.auth.login.data.source.remote.UserLoginFirebaseAuth
import com.project.musapp.feature.user.auth.login.domain.repository.UserLoginRepository
import javax.inject.Inject

class UserLoginRepositoryImp @Inject constructor(private val remoteDataSource: UserLoginFirebaseAuth) :
    UserLoginRepository {
    override suspend fun verifyUserLogin(email: String, password: String) =
        remoteDataSource.verifyUserLogin(email = email, password = password)
}