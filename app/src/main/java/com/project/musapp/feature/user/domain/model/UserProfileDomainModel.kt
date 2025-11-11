package com.project.musapp.feature.user.domain.model

import android.net.Uri
import com.project.musapp.feature.user.presentation.model.UserProfileUiModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class UserProfileDomainModel(
    val name: String,
    val surnames: String,
    val birthdate: LocalDate,
    val email: String,
    val profileImageUrl: Uri
)

fun UserProfileDomainModel.toUiModel() =
    UserProfileUiModel(
        name = this.name,
        surnames = this.surnames,
        birthdateText = this.birthdate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
        email = this.email,
        profileImageUrl = this.profileImageUrl
    )