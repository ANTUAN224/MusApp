package com.project.musapp.feature.user.registration.data.model.dto

data class UserRegistrationDTO(
    val name: String,
    val surnames: String,
    val birthdate: String,
    val email: String,
    val firebaseStorageProfileImageUrl : String
)