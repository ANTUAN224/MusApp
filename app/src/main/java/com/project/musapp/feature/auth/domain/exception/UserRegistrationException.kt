package com.project.musapp.feature.auth.domain.exception

sealed class UserRegistrationException() : Exception("") {
    data object EmailAlreadyInUseException: UserRegistrationException()
}