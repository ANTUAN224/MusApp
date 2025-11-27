package com.project.musapp.core.network.domain.exception

sealed class NetworkException : RuntimeException("") {
    data object NoInternetConnectionException : NetworkException()
}