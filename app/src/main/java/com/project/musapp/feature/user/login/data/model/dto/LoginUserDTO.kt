package com.project.musapp.feature.user.login.data.model.dto

import com.project.musapp.feature.user.login.domain.model.LoginUserModel

data class LoginUserDTO(val email: String, val password: String)

fun LoginUserModel.toRemoteDTO() = LoginUserDTO(email = email, password = password)