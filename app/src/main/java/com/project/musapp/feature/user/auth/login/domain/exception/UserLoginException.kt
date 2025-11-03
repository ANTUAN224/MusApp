package com.project.musapp.feature.user.auth.login.domain.exception

sealed class UserLoginException(message: String? = null) : RuntimeException(message) {
    data object UserNotFoundException : UserLoginException()
}