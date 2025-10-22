package com.project.musapp.feature.user.auth.login.data.model.dto

import com.project.musapp.feature.user.auth.login.domain.model.LoginUserModel

data class LoginUserDTO(val email: String, val password: String)

fun LoginUserModel.toRemoteDTO() = LoginUserDTO(email = email, password = password)