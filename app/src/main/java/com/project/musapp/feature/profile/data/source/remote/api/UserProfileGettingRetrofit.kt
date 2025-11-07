package com.project.musapp.feature.profile.data.source.remote.api

import com.project.musapp.feature.profile.data.model.dto.UserProfileDTO
import javax.inject.Inject

class UserProfileGettingRetrofit @Inject constructor(
    private val userProfileApiService: UserProfileApiService
) {
    suspend fun getUserProfile(firebaseUserToken: String): UserProfileDTO =
        userProfileApiService.getUserProfile(
            headerCompanionValue = "Bearer $firebaseUserToken"
        ).body()!!
}