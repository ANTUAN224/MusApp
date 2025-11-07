package com.project.musapp.core.network.domain.exception

sealed class NetworkException(message: String? = null) : RuntimeException(message) {
    data object NoInternetConnectionException : NetworkException()
}