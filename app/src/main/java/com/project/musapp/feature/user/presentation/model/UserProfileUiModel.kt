package com.project.musapp.feature.user.presentation.model

import android.net.Uri

data class UserProfileUiModel (
    val name: String,
    val surnames: String,
    val birthdateText : String,
    val email: String,
    val profileImageUrl: Uri
)