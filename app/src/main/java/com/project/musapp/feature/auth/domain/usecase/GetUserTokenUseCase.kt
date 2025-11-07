package com.project.musapp.feature.auth.domain.usecase

import com.project.musapp.feature.auth.domain.repository.UserAuthRepository
import javax.inject.Inject

class GetUserTokenUseCase @Inject constructor(private val repository: UserAuthRepository) {
    suspend operator fun invoke(): Result<String> {
        return runCatching { this.repository.getUserToken() }
    }
}