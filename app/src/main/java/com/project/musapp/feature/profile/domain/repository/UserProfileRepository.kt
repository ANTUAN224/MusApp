package com.project.musapp.feature.profile.domain.repository

import com.project.musapp.feature.profile.domain.model.UserProfileDomainModel

interface UserProfileRepository {
    suspend fun getUserProfile(userToken: String) : UserProfileDomainModel
}