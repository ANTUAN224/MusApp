package com.project.musapp.feature.home.user.profile.data.source.remote.service

import com.project.musapp.feature.user.register.data.model.remote.dto.RegisterUserRemoteDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserProfileApiService {
    @POST("users")
    suspend fun insertUser(@Body registerUserRemoteDto: RegisterUserRemoteDTO) : Response<Void>
}