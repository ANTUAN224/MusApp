package com.project.musapp.feature.user.auth.registration.data.repository

import android.net.Uri
import com.project.musapp.feature.user.auth.registration.data.source.remote.UserRegistrationFirebaseAuth
import com.project.musapp.feature.user.auth.registration.data.source.remote.UserRegistrationFirebaseStorage
import com.project.musapp.feature.user.auth.registration.data.source.remote.UserRegistrationRetrofit
import com.project.musapp.feature.user.auth.registration.domain.model.UserRegistrationModel
import com.project.musapp.feature.user.auth.registration.domain.repository.UserRegistrationRepository
import javax.inject.Inject

class UserRegistrationRepositoryImp @Inject constructor(
    private val userRegistrationFirebaseAuth: UserRegistrationFirebaseAuth,
    private val userRegistrationFirebaseStorage: UserRegistrationFirebaseStorage,
    private val userRegistrationRetrofit: UserRegistrationRetrofit
) :
    UserRegistrationRepository {
    override suspend fun createUser(email: String, password: String) =
        this.userRegistrationFirebaseAuth.createUser(email = email, password = password)

    override suspend fun getProfileImageUrl(profileImageLocalPath: Uri) =
        this.userRegistrationFirebaseStorage.uploadUserProfileImage(profileImageLocalPath)

    override suspend fun insertUser(
        userToken: String,
        userRegisterModel: UserRegistrationModel
    ) =
        this.userRegistrationRetrofit.insertUser(
            firebaseUserToken = userToken,
            userRegistrationModel = userRegisterModel
        )
}