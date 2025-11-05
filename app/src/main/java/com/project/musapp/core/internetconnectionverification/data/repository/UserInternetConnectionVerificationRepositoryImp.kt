package com.project.musapp.core.internetconnectionverification.data.repository

import com.project.musapp.core.internetconnectionverification.data.source.local.UserInternetConnectionVerificationAndroid
import com.project.musapp.core.internetconnectionverification.domain.exception.InternetConnectionVerificationException
import com.project.musapp.core.internetconnectionverification.domain.repository.UserInternetConnectionVerificationRepository
import javax.inject.Inject

class UserInternetConnectionVerificationRepositoryImp @Inject constructor(
    private val userInternetConnectionVerificationAndroid: UserInternetConnectionVerificationAndroid
) :
    UserInternetConnectionVerificationRepository {
    override fun verifyUserInternetConnection() {
        if (!userInternetConnectionVerificationAndroid.verifyInternetConnection()) {
            throw InternetConnectionVerificationException.NoInternetConnectionException
        }
    }
}