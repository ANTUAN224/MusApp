package com.project.musapp.core.feature.logout.domain.usecase

import com.project.musapp.core.feature.logout.domain.repository.UserLogoutRepository
import javax.inject.Inject

class LogoutUserUseCase @Inject constructor(private val repository: UserLogoutRepository) {
    operator fun invoke () = this.repository.logoutUser()
}