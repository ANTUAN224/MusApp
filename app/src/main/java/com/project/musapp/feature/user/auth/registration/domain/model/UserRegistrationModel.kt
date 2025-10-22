package com.project.musapp.feature.user.auth.registration.domain.model

import com.project.musapp.feature.user.auth.registration.data.model.dto.UserRegistrationDTO

class UserRegistrationModel(
    val name: String,
    val surnames: String,
    val birthdateText: String,
    val email: String,
    val profileImageUrl: String
)

fun UserRegistrationModel.toDTO(): UserRegistrationDTO =
    UserRegistrationDTO(
        name = this.name,
        surnames = this.surnames,
        birthdate = this.birthdateText,
        email = this.email,
        profileImageUrl = this.profileImageUrl
    )