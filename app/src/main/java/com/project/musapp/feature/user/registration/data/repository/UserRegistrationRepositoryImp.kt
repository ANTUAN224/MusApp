package com.project.musapp.feature.user.registration.data.repository

import com.project.musapp.feature.user.registration.data.source.remote.UserRegistrationRemoteDataSource
import com.project.musapp.feature.user.registration.domain.model.UserRegistrationModel
import com.project.musapp.feature.user.registration.domain.repository.UserRegistrationRepository
import javax.inject.Inject

class UserRegistrationRepositoryImp @Inject constructor(
    private val remoteDataSource: UserRegistrationRemoteDataSource
) :
    UserRegistrationRepository {
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