package com.project.musapp.feature.user.data.model.dto

import androidx.core.net.toUri
import com.google.gson.annotations.SerializedName
import com.project.musapp.feature.user.domain.model.UserProfileDomainModel
import java.time.LocalDate

data class UserProfileDTO(
    val name: String,
    val surnames: String,
    @SerializedName("birthdate")
    val birthdateText: String,
    val email: String,
    val profileImageUrlText: String
)

fun UserProfileDTO.toDomainModel() =
    UserProfileDomainModel(
        name = this.name,
        surnames = this.surnames,
        birthdate = LocalDate.parse(this.birthdateText),
        email = this.email,
        profileImageUrl = this.profileImageUrlText.toUri()
    )