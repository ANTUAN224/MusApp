package com.project.musapp.feature.user.login.data.repository

import com.project.musapp.feature.user.login.data.source.remote.UserLoginRemoteDataSource
import com.project.musapp.feature.user.login.domain.repository.UserLoginRepository
import javax.inject.Inject

class UserLoginRepositoryImp @Inject constructor(private val remoteDataSource: UserLoginRemoteDataSource) :
    UserLoginRepository {
    override suspend fun verifyUserLogin(email: String, password: String) =
        remoteDataSource.verifyUserLogin(email = email, password = password)
}