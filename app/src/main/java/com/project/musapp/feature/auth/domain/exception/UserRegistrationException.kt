package com.project.musapp.feature.auth.domain.exception

sealed class UserRegistrationException(message: String? = null) : Exception(message) {
    data object EmailAlreadyInUseException: UserRegistrationException()
}