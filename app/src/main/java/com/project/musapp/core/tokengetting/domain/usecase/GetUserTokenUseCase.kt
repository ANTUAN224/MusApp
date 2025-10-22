package com.project.musapp.core.tokengetting.domain.usecase

import com.project.musapp.core.tokengetting.domain.repository.UserTokenGettingRepository
import javax.inject.Inject

class GetUserTokenUseCase @Inject constructor(private val repository: UserTokenGettingRepository) {
    suspend operator fun invoke(): String = this.repository.getUserToken()
}