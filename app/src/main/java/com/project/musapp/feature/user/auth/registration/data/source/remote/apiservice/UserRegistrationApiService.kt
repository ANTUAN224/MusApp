package com.project.musapp.feature.user.auth.registration.data.source.remote.apiservice

import com.project.musapp.feature.user.auth.registration.data.model.dto.UserRegistrationDTO
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UserRegistrationApiService {
    @POST("user/registration")
    suspend fun insertUser(
        @Header("Authorization") headerCompanionValue: String,
        @Body userRegistrationDTO: UserRegistrationDTO
    )
}