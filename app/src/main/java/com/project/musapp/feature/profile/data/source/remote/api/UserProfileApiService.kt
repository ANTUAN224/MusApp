package com.project.musapp.feature.profile.data.source.remote.api

import com.project.musapp.feature.profile.data.model.dto.UserProfileDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UserProfileApiService {
    @GET("users/profile")
    suspend fun getUserProfile(
        @Header("Authorization") headerCompanionValue: String
    ): Response<UserProfileDTO>
}