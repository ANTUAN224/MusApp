package com.project.musapp.feature.user.auth.login.data.repository

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuthException
import com.project.musapp.core.internetconnectionverification.domain.exception.InternetConnectionVerificationException
import com.project.musapp.feature.user.auth.login.data.source.remote.UserLoginFirebaseAuth
import com.project.musapp.feature.user.auth.login.domain.exception.UserLoginException
import com.project.musapp.feature.user.auth.login.domain.repository.UserLoginRepository
import kotlinx.coroutines.TimeoutCancellationException
import javax.inject.Inject

class UserLoginRepositoryImp @Inject constructor(private val userLoginFirebaseAuth: UserLoginFirebaseAuth) :
    UserLoginRepository {
    override suspend fun logInUser(email: String, password: String) {
        try {
            userLoginFirebaseAuth.logInUser(email = email, password = password)
        } catch (e: Exception) {
            when (e) {
                is FirebaseNetworkException, is TimeoutCancellationException ->
                    throw InternetConnectionVerificationException.NoInternetConnectionException

                is FirebaseAuthException -> throw UserLoginException.UserNotFoundException
            }
        }
    }
}