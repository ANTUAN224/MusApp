package com.project.musapp.feature.user.helper

object RegisterOrLoginRegexHelper {
    val nameOrSurnameRegex = Regex(pattern = "^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s?([A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s?)?$")
    val emailRegex = Regex(pattern = "^[a-z0-9]+@(gmail|outlook)+\\.(com|es)\\s?$")
    val passwordRegex = Regex(pattern = "^[A-Za-z0-9*+-_#?@]+$")
}