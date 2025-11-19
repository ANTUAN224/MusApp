package com.project.musapp.feature.auth.data.repository

import android.net.Uri
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.storage.StorageException
import com.project.musapp.core.network.domain.exception.NetworkException
import com.project.musapp.feature.auth.data.source.local.UserLogoutFirebaseAuth
import com.project.musapp.feature.auth.data.source.local.UserSessionStateVerificationFirebaseAuth
import com.project.musapp.feature.auth.data.source.remote.UserLoginFirebaseAuth
import com.project.musapp.feature.auth.data.source.remote.UserRegistrationFirebaseAuth
import com.project.musapp.feature.auth.data.source.remote.UserRegistrationFirebaseStorage
import com.project.musapp.feature.auth.data.source.remote.api.UserRegistrationRetrofit
import com.project.musapp.feature.auth.data.source.remote.UserTokenGettingFirebaseAuth
import com.project.musapp.feature.auth.domain.exception.UserLoginException
import com.project.musapp.feature.auth.domain.exception.UserRegistrationException
import com.project.musapp.feature.auth.domain.model.UserRegistrationDomainModel
import com.project.musapp.feature.auth.domain.model.toDTO
import com.project.musapp.feature.auth.domain.repository.UserAuthRepository
import kotlinx.coroutines.TimeoutCancellationException
import java.io.IOException
import javax.inject.Inject

class UserAuthRepositoryImp @Inject constructor(
    private val userRegistrationFirebaseAuth: UserRegistrationFirebaseAuth,
    private val userRegistrationFirebaseStorage: UserRegistrationFirebaseStorage,
    private val userRegistrationRetrofit: UserRegistrationRetrofit,
    private val userLoginFirebaseAuth: UserLoginFirebaseAuth,
    private val userSessionStateVerificationFirebaseAuth: UserSessionStateVerificationFirebaseAuth,
    private val userLogoutFirebaseAuth: UserLogoutFirebaseAuth,
    private val userTokenGettingFirebaseAuth: UserTokenGettingFirebaseAuth
) : UserAuthRepository {
    override suspend fun createUser(email: String, password: String) {
        try {
            userRegistrationFirebaseAuth.createUser(email = email, password = password)
        } catch (_: FirebaseNetworkException) {
            throw NetworkException.NoInternetConnectionException
        } catch (_: FirebaseAuthUserCollisionException) {
            throw UserRegistrationException.EmailAlreadyInUseException
        }
    }

    override suspend fun uploadUserProfileImage(profileImageLocalPath: Uri): String {
        try {
            return userRegistrationFirebaseStorage.uploadUserProfileImage(profileImageLocalPath)
        } catch (_: StorageException) {
            throw NetworkException.NoInternetConnectionException
        }
    }

    override suspend fun insertUser(
        userToken: String,
        userRegistrationDomainModel: UserRegistrationDomainModel
    ) {
        try {
            userRegistrationRetrofit.insertUser(
                userToken = userToken,
                userRegistrationDTO = userRegistrationDomainModel.toDTO()
            )
        } catch (_: IOException) {
            throw NetworkException.NoInternetConnectionException
        }
    }

    override suspend fun logInUser(email: String, password: String) {
        try {
            userLoginFirebaseAuth.logInUser(email = email, password = password)
        } catch (e: Exception) {
            when (e) {
                is FirebaseNetworkException, is TimeoutCancellationException ->
                    throw NetworkException.NoInternetConnectionException

                is FirebaseAuthException -> throw UserLoginException.UserNotFoundException
            }
        }
    }

    override fun verifyUserSession() =
        userSessionStateVerificationFirebaseAuth.verifyUserSession()

    override fun logOutUser() {
        userLogoutFirebaseAuth.logOutUser()
    }

    override suspend fun getUserToken(): String {
        try {
            return userTokenGettingFirebaseAuth.getFirebaseUserToken()
        } catch (_: FirebaseNetworkException) {
            throw NetworkException.NoInternetConnectionException
        }
    }
}