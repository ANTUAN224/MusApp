package com.project.musapp.feature.auth.domain.usecase

import android.net.Uri
import com.project.musapp.feature.auth.domain.repository.UserAuthRepository
import javax.inject.Inject

class UploadUserProfileImageUseCase @Inject constructor(private val repository: UserAuthRepository) {
    suspend operator fun invoke(profileImageLocalPath: Uri): Result<String> {
        return runCatching {
            repository.uploadUserProfileImage(profileImageLocalPath = profileImageLocalPath)
        }
    }
}