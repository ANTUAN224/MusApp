package com.project.musapp.feature.auth.data.source.remote

import com.project.musapp.feature.user.auth.data.model.dto.UserRegistrationDTO
import com.project.musapp.feature.user.auth.data.source.remote.apiservice.UserAuthApiService
import javax.inject.Inject

class UserRegistrationRetrofit @Inject constructor(
    private val userAuthApiService: UserAuthApiService
) {
    suspend fun insertUser(
        firebaseUserToken: String,
        userRegistrationDTO: UserRegistrationDTO
    ) {
        userAuthApiService
            .insertUser(
                headerCompanionValue = "Bearer $firebaseUserToken",
                userRegistrationDTO = userRegistrationDTO
            )
    }
}