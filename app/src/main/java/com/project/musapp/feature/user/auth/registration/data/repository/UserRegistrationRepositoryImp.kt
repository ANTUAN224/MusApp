package com.project.musapp.feature.user.auth.registration.data.repository

import android.net.Uri
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.storage.StorageException
import com.project.musapp.core.internetconnectionverification.domain.exception.InternetConnectionVerificationException
import com.project.musapp.feature.user.auth.registration.data.source.remote.UserRegistrationFirebaseAuth
import com.project.musapp.feature.user.auth.registration.data.source.remote.UserRegistrationFirebaseStorage
import com.project.musapp.feature.user.auth.registration.data.source.remote.UserRegistrationRetrofit
import com.project.musapp.feature.user.auth.registration.domain.exception.UserRegistrationException
import com.project.musapp.feature.user.auth.registration.domain.model.UserRegistrationDomainModel
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
            throw InternetConnectionVerificationException.NoInternetConnectionException
        } catch (_: FirebaseAuthUserCollisionException) {
            throw UserRegistrationException.EmailAlreadyInUseException
        }
    }

    override suspend fun getProfileImageUrlText(profileImageLocalPath: Uri): String {
        try {
            return userRegistrationFirebaseStorage.uploadUserProfileImage(profileImageLocalPath)
        } catch (_: StorageException) {
            throw InternetConnectionVerificationException.NoInternetConnectionException
        }
    }

    override suspend fun insertUser(
        userToken: String,
        userRegistrationDomainModel: UserRegistrationDomainModel
    ) {
        try {
            userRegistrationRetrofit.insertUser(
                firebaseUserToken = userToken,
                userRegistrationDomainModel = userRegistrationDomainModel
            )
        } catch (_: IOException) {
            throw InternetConnectionVerificationException.NoInternetConnectionException
        }
    }
}