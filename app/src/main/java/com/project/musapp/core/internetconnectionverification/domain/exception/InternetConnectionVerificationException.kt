package com.project.musapp.core.internetconnectionverification.domain.exception

sealed class InternetConnectionVerificationException(message: String? = null) : RuntimeException(message) {
    data object NoInternetConnectionException : InternetConnectionVerificationException()
}