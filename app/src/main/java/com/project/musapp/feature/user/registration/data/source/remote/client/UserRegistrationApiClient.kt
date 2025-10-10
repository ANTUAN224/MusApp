package com.project.musapp.feature.user.registration.data.source.remote.client

import com.project.musapp.feature.home.user.profile.data.model.dto.UserProfileDTO
import com.project.musapp.feature.user.registration.data.model.dto.UserRegistrationDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UserRegistrationApiClient {
    @POST("user/register")
    suspend fun insertUser(
        @Header("Authorization") headerCompanionValue: String,
        @Body userRegistrationDTO: UserRegistrationDTO
    ): Response<UserProfileDTO>
}