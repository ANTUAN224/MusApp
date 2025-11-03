package com.project.musapp.feature.user.auth.registration.domain.exception

sealed class UserRegistrationException(message: String? = null) : Exception(message) {
    data object EmailAlreadyInUseException: UserRegistrationException()
}