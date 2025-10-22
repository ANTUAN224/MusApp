package com.project.musapp.feature.user.auth.registration.domain.repository

import android.net.Uri
import com.project.musapp.feature.user.auth.registration.domain.model.UserRegistrationModel

interface UserRegistrationRepository {
    suspend fun createUser(email: String, password: String)

    suspend fun getProfileImageUrl(profileImageLocalPath: Uri): String

    suspend fun insertUser(
        userToken: String,
        userRegisterModel: UserRegistrationModel
    )
}