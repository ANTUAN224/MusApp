package com.project.musapp.feature.user.register.data.model.remote.dto

import com.project.musapp.feature.user.register.domain.model.RegisterUserModel

data class RegisterUserRemoteDTO(
    val name: String,
    val surnames: String,
    val birthdate: String,
    val email: String,
    val imageFirebaseUrl: String
)

fun RegisterUserModel.toRemoteDTO(userProfileImageUrl: String) = RegisterUserRemoteDTO(
    name = name,
    surnames = surnames,
    birthdate = birthdate.toString(),
    email = email,
    imageFirebaseUrl = userProfileImageUrl
)