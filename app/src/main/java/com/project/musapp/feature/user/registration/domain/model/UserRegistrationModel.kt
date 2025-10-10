package com.project.musapp.feature.user.registration.domain.model

import com.project.musapp.feature.user.registration.data.model.dto.UserRegistrationDTO

class UserRegistrationModel(
    val name: String,
    val surnames: String,
    val birthdateText: String,
    val email: String
)

fun UserRegistrationModel.toDTO(): UserRegistrationDTO =
    UserRegistrationDTO(
        name = name,
        surnames = surnames,
        birthdate = birthdateText,
        email = email
    )