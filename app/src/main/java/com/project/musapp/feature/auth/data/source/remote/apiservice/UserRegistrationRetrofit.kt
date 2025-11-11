package com.project.musapp.feature.auth.data.source.remote.apiservice

import com.project.musapp.feature.auth.data.model.dto.UserRegistrationDTO
import javax.inject.Inject

class UserRegistrationRetrofit @Inject constructor(
    private val userAuthApiService: UserAuthApiService
) {
    suspend fun insertUser(
        userToken: String,
        userRegistrationDTO: UserRegistrationDTO
    ) {
        userAuthApiService
            .insertUser(
                headerCompanionValue = "Bearer $userToken",
                userRegistrationDTO = userRegistrationDTO
            )
    }
}