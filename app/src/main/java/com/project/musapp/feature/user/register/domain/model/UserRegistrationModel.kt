package com.project.musapp.feature.user.register.domain.model

import android.net.Uri
import java.time.LocalDate

class UserRegistrationModel(
    val name: String,
    val surnames: String,
    val birthdate: LocalDate,
    val email: String,
    val password: String,
    val imagePath: Uri
)