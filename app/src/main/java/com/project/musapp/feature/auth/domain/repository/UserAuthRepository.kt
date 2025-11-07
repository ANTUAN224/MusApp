package com.project.musapp.feature.auth.domain.repository

import android.net.Uri
import com.project.musapp.feature.auth.domain.model.UserRegistrationDomainModel

interface UserAuthRepository {
    suspend fun createUser(email: String, password: String)

    suspend fun uploadUserProfileImage(profileImageLocalPath: Uri): String

    suspend fun insertUser(
        userToken: String,
        userRegistrationDomainModel: UserRegistrationDomainModel
    )

    suspend fun logInUser(email: String, password: String)

    fun verifyUserSession() : Boolean

    fun logOutUser()

    suspend fun getUserToken() : String
}