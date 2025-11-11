package com.project.musapp.feature.auth.domain.exception

sealed class UserLoginException() : RuntimeException("") {
    data object UserNotFoundException : UserLoginException()
}