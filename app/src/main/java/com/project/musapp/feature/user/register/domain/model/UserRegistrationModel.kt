package com.project.musapp.feature.user.register.domain.model

import com.project.musapp.feature.user.register.data.model.dto.UserRegistrationDTO
import java.time.LocalDate

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