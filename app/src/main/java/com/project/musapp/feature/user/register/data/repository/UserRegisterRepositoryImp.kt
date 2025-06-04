package com.project.musapp.feature.user.register.data.repository

import com.project.musapp.feature.user.register.data.source.local.RegisterUserLocalDataSource
import com.project.musapp.feature.user.register.data.source.remote.RegisterUserRemoteDataSource
import com.project.musapp.feature.user.register.domain.model.UserRegisterModel
import com.project.musapp.feature.user.register.domain.repository.UserRegisterRepository
import javax.inject.Inject

class UserRegisterRepositoryImp @Inject constructor(
    private val remoteDataSource: RegisterUserRemoteDataSource,
    private val localDataSource: RegisterUserLocalDataSource
) :
    UserRegisterRepository {
    override suspend fun createToken(email: String, password: String) =
        remoteDataSource.createUserInFirebaseAuth(
            email = email,
            password = password
        )

    override suspend fun insertUser(
        userRegisterModel: UserRegisterModel
    ): Boolean {
        return remoteDataSource.insertUser(userRegisterModel = userRegisterModel) &&
                localDataSource.insertUser(
                    userRegisterModel = userRegisterModel
                )
    }
}