package com.project.musapp.feature.user.auth.registration.data.source.remote

import com.project.musapp.feature.user.auth.registration.data.source.remote.apiservice.UserRegistrationApiService
import com.project.musapp.feature.user.auth.registration.domain.model.UserRegistrationDomainModel
import com.project.musapp.feature.user.auth.registration.domain.model.toDTO
import javax.inject.Inject

class UserRegistrationRetrofit @Inject constructor(
    private val userRegistrationApiService: UserRegistrationApiService
) {
    suspend fun insertUser(
        firebaseUserToken: String,
        userRegistrationDomainModel: UserRegistrationDomainModel
    ) {
        userRegistrationApiService
            .insertUser(
                headerCompanionValue = "Bearer $firebaseUserToken",
                userRegistrationDTO = userRegistrationDomainModel.toDTO()
            )
    }
}