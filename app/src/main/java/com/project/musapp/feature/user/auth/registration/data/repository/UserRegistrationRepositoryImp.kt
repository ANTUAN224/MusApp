package com.project.musapp.feature.user.auth.registration.data.repository

import android.net.Uri
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.storage.StorageException
import com.project.musapp.core.internetconnectionverification.domain.exception.NoInternetConnectionException
import com.project.musapp.feature.user.auth.registration.data.source.remote.UserRegistrationFirebaseAuth
import com.project.musapp.feature.user.auth.registration.data.source.remote.UserRegistrationFirebaseStorage
import com.project.musapp.feature.user.auth.registration.data.source.remote.UserRegistrationRetrofit
import com.project.musapp.feature.user.auth.registration.domain.exception.EmailAlreadyInUseException
import com.project.musapp.feature.user.auth.registration.domain.model.UserRegistrationModel
import com.project.musapp.feature.user.auth.registration.domain.model.toDTO
import com.project.musapp.feature.user.auth.registration.domain.repository.UserRegistrationRepository
import java.io.IOException
import javax.inject.Inject

class UserRegistrationRepositoryImp @Inject constructor(
    private val userRegistrationFirebaseAuth: UserRegistrationFirebaseAuth,
    private val userRegistrationFirebaseStorage: UserRegistrationFirebaseStorage,
    private val userRegistrationRetrofit: UserRegistrationRetrofit
) :
    UserRegistrationRepository {
    override suspend fun createUser(email: String, password: String) {
        try {
            userRegistrationFirebaseAuth.createUser(email = email, password = password)
        } catch (_: FirebaseNetworkException) {
            throw NoInternetConnectionException()
        } catch (_: FirebaseAuthUserCollisionException) {
            throw EmailAlreadyInUseException()
        }
    }

    override suspend fun getProfileImageUrl(profileImageLocalPath: Uri): String {
        try {
            return userRegistrationFirebaseStorage.uploadUserProfileImage(profileImageLocalPath)
        } catch (_: StorageException) {
            throw NoInternetConnectionException()
        }
    }

    override suspend fun insertUser(
        userToken: String,
        userRegisterModel: UserRegistrationModel
    ) {
        try {
            userRegistrationRetrofit.insertUser(
                firebaseUserToken = userToken,
                userRegistrationDTO = userRegisterModel.toDTO()
            )
        } catch (_: IOException) {
            throw NoInternetConnectionException()
        }
    }
}