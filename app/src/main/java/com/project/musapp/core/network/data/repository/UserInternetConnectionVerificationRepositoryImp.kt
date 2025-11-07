package com.project.musapp.core.network.data.repository

import com.project.musapp.core.network.data.source.local.UserInternetConnectionVerificationAndroid
import com.project.musapp.core.network.domain.exception.NetworkException
import com.project.musapp.core.network.domain.repository.UserInternetConnectionVerificationRepository
import javax.inject.Inject

class UserInternetConnectionVerificationRepositoryImp @Inject constructor(
    private val userInternetConnectionVerificationAndroid: UserInternetConnectionVerificationAndroid
) :
    UserInternetConnectionVerificationRepository {
    override fun verifyUserInternetConnection() {
        if (!userInternetConnectionVerificationAndroid.verifyInternetConnection()) {
            throw NetworkException.NoInternetConnectionException
        }
    }
}