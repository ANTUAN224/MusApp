package com.project.musapp.feature.user.auth.registration.domain.repository

import android.net.Uri
import com.project.musapp.feature.user.auth.registration.domain.model.UserRegistrationDomainModel

interface UserRegistrationRepository {
    suspend fun createUser(email: String, password: String)

    suspend fun getProfileImageUrlText(profileImageLocalPath: Uri): String

    suspend fun insertUser(
        userToken: String,
        userRegistrationDomainModel: UserRegistrationDomainModel
    )
}