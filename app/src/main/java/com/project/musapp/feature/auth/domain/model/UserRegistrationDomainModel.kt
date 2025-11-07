package com.project.musapp.feature.auth.domain.model

import com.project.musapp.feature.user.auth.data.model.dto.UserRegistrationDTO

data class UserRegistrationDomainModel(
    val name: String,
    val surnames: String,
    val birthdateText: String,
    val email: String,
    val profileImageUrlText: String
)

fun UserRegistrationDomainModel.toDTO() =
    UserRegistrationDTO(
        name = this.name,
        surnames = this.surnames,
        birthdateText = this.birthdateText,
        email = this.email,
        profileImageUrlText = this.profileImageUrlText
    )