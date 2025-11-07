package com.project.musapp.feature.auth.domain.usecase

import com.project.musapp.feature.auth.domain.repository.UserAuthRepository
import javax.inject.Inject

class LogOutUserUseCase @Inject constructor(private val repository: UserAuthRepository) {
    operator fun invoke() = this.repository.logOutUser()
}