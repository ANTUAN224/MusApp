package com.project.musapp.feature.auth.helper

object RegexHelper {
    val nameOrSurnameRegex = Regex(pattern = "^[A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s?([A-ZÁÉÍÓÚÑ][a-záéíóúñ]+\\s?)?$")
    val emailRegex = Regex(pattern = "^[a-z0-9]+@(gmail|hotmail|yahoo)+\\.(com|es)\\s?$")
}