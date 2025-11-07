package com.project.musapp.feature.auth.data.source.remote.apiservice

import com.project.musapp.feature.auth.data.model.dto.UserRegistrationDTO
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UserAuthApiService {
    @POST("user/registration")
    suspend fun insertUser(
        @Header("Authorization") headerCompanionValue: String,
        @Body userRegistrationDTO: UserRegistrationDTO
    )
}