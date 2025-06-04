package com.project.musapp.feature.user.login.data.source.remote.service

import com.project.musapp.feature.user.login.data.model.remote.dto.LoginUserRemoteDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface LoginUserApiService {
    @GET("get/user/email/{}/password/{}")
    suspend fun getUserByEmailAndPassword(@Body loginUserRemoteDTO: LoginUserRemoteDTO) : Response<LoginUserRemoteDTO>
}