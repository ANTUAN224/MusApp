package com.project.musapp.feature.user.auth.registration.data.source.remote

import com.project.musapp.core.internetconnectionverification.domain.exception.NoInternetConnectionException
import com.project.musapp.feature.user.auth.registration.data.model.dto.UserRegistrationDTO
import com.project.musapp.feature.user.auth.registration.data.source.remote.service.UserRegistrationApiService
import com.project.musapp.feature.user.auth.registration.domain.model.UserRegistrationModel
import java.io.IOException
import javax.inject.Inject

class UserRegistrationRetrofit @Inject constructor(
    private val userRegistrationApiService: UserRegistrationApiService
) {
    suspend fun insertUser(
        firebaseUserToken: String,
        userRegistrationDTO: UserRegistrationDTO
    ) {
        userRegistrationApiService
            .insertUser(
                headerCompanionValue = "Bearer $firebaseUserToken",
                userRegistrationDTO = userRegistrationDTO
            )
    }
}