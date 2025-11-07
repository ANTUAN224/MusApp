package com.project.musapp.feature.auth.domain.exception

sealed class UserLoginException(message: String? = null) : RuntimeException(message) {
    data object UserNotFoundException : UserLoginException()
}