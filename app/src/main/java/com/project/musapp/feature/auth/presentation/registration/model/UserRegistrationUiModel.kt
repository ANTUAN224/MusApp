package com.project.musapp.feature.auth.presentation.registration.model

import com.project.musapp.feature.auth.domain.model.UserRegistrationDomainModel

data class UserRegistrationUiModel(
    val name: String,
    val surnames: String,
    val birthdateText: String,
    val email: String,
    val profileImageUrlText: String
)

fun UserRegistrationUiModel.toDomainModel() =
    UserRegistrationDomainModel(
        name = this.name,
        surnames = this.surnames,
        birthdateText = this.birthdateText,
        email = this.email,
        profileImageUrlText = this.profileImageUrlText
    )