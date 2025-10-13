package com.project.musapp.feature.user.registration.domain.model

import android.net.Uri
import com.project.musapp.feature.user.registration.data.model.dto.UserRegistrationDTO

class UserRegistrationModel(
    val name: String,
    val surnames: String,
    val birthdateText: String,
    val email: String,
    val profileImageLocalPath: Uri
)

fun UserRegistrationModel.toDTO(firebaseStorageProfileImageUrl: String): UserRegistrationDTO =
    UserRegistrationDTO(
        name = name,
        surnames = surnames,
        birthdate = birthdateText,
        email = email,
        firebaseStorageProfileImageUrl = firebaseStorageProfileImageUrl
    )