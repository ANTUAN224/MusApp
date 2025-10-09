package com.project.musapp.feature.user.register.data.repository

import com.project.musapp.feature.user.register.data.source.remote.UserRegistrationRemoteDataSource
import com.project.musapp.feature.user.register.domain.model.UserRegistrationModel
import com.project.musapp.feature.user.register.domain.repository.UserRegisterRepository
import javax.inject.Inject

class RegisterUserRepositoryImp @Inject constructor(
    private val remoteDataSource: UserRegistrationRemoteDataSource
) :
    UserRegisterRepository {
    override suspend fun createFirebaseUser(email: String, password: String) =
        remoteDataSource.createUserInFirebase(
            email = email,
            password = password
        )

    override suspend fun insertUser(
        userRegisterModel: UserRegistrationModel
    ): Boolean {
        return remoteDataSource.insertUser(userRegistrationModel = userRegisterModel)
    }
}