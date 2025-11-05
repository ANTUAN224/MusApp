package com.project.musapp.feature.home.data.source.remote

import com.project.musapp.feature.home.data.model.dto.UserProfileDTO
import com.project.musapp.feature.home.data.source.remote.apiservice.HomeApiService
import javax.inject.Inject

class UserProfileGettingRetrofit @Inject constructor(
    private val homeApiService: HomeApiService
) {
    suspend fun getUserProfile(firebaseUserToken: String): UserProfileDTO =
        homeApiService.getUserProfile(
            headerCompanionValue = "Bearer $firebaseUserToken"
        ).body()!!
}