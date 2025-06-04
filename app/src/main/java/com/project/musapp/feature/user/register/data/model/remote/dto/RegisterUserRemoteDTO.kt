package com.project.musapp.feature.user.register.data.model.remote.dto

import com.google.gson.annotations.SerializedName
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

data class RegisterUserRemoteDTO @AssistedInject constructor(
    @SerializedName(value = "user_name")
    @Assisted(value = "name")
    val name: String,
    @SerializedName(value = "user_surnames")
    @Assisted(value = "surnames")
    val surnames: String,
    @SerializedName(value = "user_birthdate")
    @Assisted(value = "birthdate")
    val birthdate: String,
    @SerializedName(value = "user_email")
    @Assisted(value = "email")
    val email: String,
    @SerializedName(value = "user_password")
    @Assisted(value = "password")
    val password: String,
    @SerializedName(value = "user_image")
    @Assisted(value = "imagePath")
    val imagePath: String
)