package com.project.musapp.feature.user.register.data.repository

import com.project.musapp.feature.user.register.data.source.remote.RegisterUserRemoteDataSource
import com.project.musapp.feature.user.register.domain.model.RegisterUserModel
import com.project.musapp.feature.user.register.domain.repository.UserRegisterRepository
import javax.inject.Inject

class RegisterUserRepositoryImp @Inject constructor(
    private val remoteDataSource: RegisterUserRemoteDataSource
) :
    UserRegisterRepository {
    override suspend fun createFirebaseUser(email: String, password: String) =
        remoteDataSource.createUserInFirebase(
            email = email,
            password = password
        )

    override suspend fun insertUser(
        userRegisterModel: RegisterUserModel
    ): Boolean {
//        return remoteDataSource.insertUser(registerUserModel = userRegisterModel)
////                &&
////                localDataSource.insertUser(
////                    userRegisterModel = userRegisterModel
////                )
        return true
    }
}