package com.example.musapp.core.helper

import android.util.Patterns

object RegisterHelper {
    private val nameRegex = Regex("^[A-ZÁÉÍÓÚÑ]{1}[a-záéíóúñ]+$")
    private val surnameRegex = Regex("^[A-ZÁÉÍÓÚÑ]{1}[a-záéíóúñ]+(\\s+[A-Z]{1}[a-z]+)?$")
    private val passwordRegex = Regex("^[A-Za-z0-9]+$")

    fun checkUserName(userName : String) = userName.isNotBlank() && nameRegex.matches(userName)

    fun checkUserSurnames(userSurnames : String) = userSurnames.isNotBlank() && surnameRegex.matches(userSurnames)

    fun checkUserEmail(userEmail : String) = Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()

    fun checkUserPassword(userPassword : String) = passwordRegex.matches(userPassword)
}