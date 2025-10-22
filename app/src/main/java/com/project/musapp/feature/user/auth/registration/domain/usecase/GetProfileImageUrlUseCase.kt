package com.project.musapp.feature.user.auth.registration.domain.usecase

import android.net.Uri
import com.project.musapp.feature.user.auth.registration.domain.repository.UserRegistrationRepository
import javax.inject.Inject

class GetProfileImageUrlUseCase @Inject constructor(private val repository: UserRegistrationRepository) {
    suspend operator fun invoke(profileImageLocalPath: Uri) =
        this.repository.getProfileImageUrl(profileImageLocalPath = profileImageLocalPath)
}