package com.project.musapp.feature.user.register.data.repository

//import com.project.musapp.feature.user.register.data.source.local.UserRegisterLocalDataSource
import com.project.musapp.feature.user.register.data.source.remote.RegisterUserRemoteDataSource
import com.project.musapp.feature.user.register.domain.model.RegisterUserModel
import com.project.musapp.feature.user.register.domain.repository.UserRegisterRepository
import javax.inject.Inject

class UserRegisterRepositoryImp @Inject constructor(
    private val remoteDataSource: RegisterUserRemoteDataSource,
//    private val localDataSource: UserRegisterLocalDataSource
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
        return remoteDataSource.insertUser(userRegisterModel = userRegisterModel)
//                &&
//                localDataSource.insertUser(
//                    userRegisterModel = userRegisterModel
//                )
    }
}