package com.project.musapp.core.tokengetting.domain.repository

interface UserTokenGettingRepository {
    suspend fun getUserToken() : String
}