package com.project.musapp.feature.user.auth.login.data.source.remote.client

import com.project.musapp.feature.user.auth.login.data.model.dto.LoginUserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

interface UserLoginApiClient {
    @GET("")
    suspend fun getUserByEmailAndPassword(@Body loginUserDTO: LoginUserDTO) : Response<LoginUserDTO>
}