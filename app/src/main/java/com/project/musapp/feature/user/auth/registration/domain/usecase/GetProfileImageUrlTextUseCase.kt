package com.project.musapp.feature.user.auth.registration.domain.usecase

import android.net.Uri
import com.project.musapp.feature.user.auth.registration.domain.repository.UserRegistrationRepository
import javax.inject.Inject

class GetProfileImageUrlTextUseCase @Inject constructor(private val repository: UserRegistrationRepository) {
    suspend operator fun invoke(profileImageLocalPath: Uri): Result<String> {
        return runCatching { this.repository.getProfileImageUrlText(profileImageLocalPath = profileImageLocalPath) }
    }
}