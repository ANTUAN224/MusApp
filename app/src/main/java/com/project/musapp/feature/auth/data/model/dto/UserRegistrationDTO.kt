package com.project.musapp.feature.auth.data.model.dto

import com.google.gson.annotations.SerializedName

data class UserRegistrationDTO(
    val name: String,
    val surnames: String,
    @SerializedName("birthdate")
    val birthdateText: String,
    val email: String,
    val profileImageUrlText: String
)