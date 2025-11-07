package com.project.musapp.feature.profile.data.repository

import com.project.musapp.core.network.domain.exception.NetworkException
import com.project.musapp.feature.profile.data.model.dto.toDomainModel
import com.project.musapp.feature.profile.data.source.remote.api.UserProfileGettingRetrofit
import com.project.musapp.feature.profile.domain.model.UserProfileDomainModel
import com.project.musapp.feature.profile.domain.repository.UserProfileRepository
import java.io.IOException
import javax.inject.Inject

class UserProfileRepositoryImp @Inject constructor(
    private val userProfileGettingRetrofit: UserProfileGettingRetrofit,
) : UserProfileRepository {
    override suspend fun getUserProfile(userToken: String): UserProfileDomainModel {
        try {
            return userProfileGettingRetrofit.getUserProfile(
                firebaseUserToken = userToken
            ).toDomainModel()
        } catch (_: IOException) {
            throw NetworkException.NoInternetConnectionException
        }
    }
}