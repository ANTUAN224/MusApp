package com.project.musapp.feature.user.login.data.model.remote.dto

import com.project.musapp.feature.user.login.domain.model.LoginUserModel

data class LoginUserRemoteDTO(val email: String, val password: String)

fun LoginUserModel.toRemoteDTO() = LoginUserRemoteDTO(email = email, password = password)