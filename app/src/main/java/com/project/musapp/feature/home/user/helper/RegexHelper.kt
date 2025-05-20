package com.project.musapp.feature.home.user.helper

import android.util.Patterns

object RegexHelper {
    val nameRegex = Regex("^[A-ZÁÉÍÓÚÑ]{1}[a-záéíóúñ]+$")
    val surnameRegex = Regex("^[A-ZÁÉÍÓÚÑ]{1}[a-záéíóúñ]+(\\s+[A-Z]{1}[a-z]+)?$")
    val emailRegex = Regex(Patterns.EMAIL_ADDRESS.toString())
    val passwordRegex = Regex("^[A-Za-z0-9*+-_]+$")
}