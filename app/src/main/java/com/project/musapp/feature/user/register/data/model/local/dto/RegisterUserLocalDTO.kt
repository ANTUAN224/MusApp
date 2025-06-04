package com.project.musapp.feature.user.register.data.model.local.dto

import android.net.Uri
import com.project.musapp.feature.user.register.domain.model.RegisterUserModel
import java.time.LocalDate

data class RegisterUserLocalDTO(
    val name: String,
    val surnames: String,
    val birthdate: LocalDate,
    val email: String,
    val imagePath: Uri
)

fun RegisterUserModel.toLocalDTO() = RegisterUserLocalDTO(
    name = name,
    surnames = surnames,
    birthdate = birthdate,
    email = email,
    imagePath = imagePath
)
