package com.project.musapp.core.tokengetting.data.repository

import com.project.musapp.core.tokengetting.data.source.remote.UserTokenGettingFirebaseAuth
import com.project.musapp.core.tokengetting.domain.repository.UserTokenGettingRepository
import javax.inject.Inject

class UserTokenGettingRepositoryImp @Inject constructor(
    private val userTokenGettingFirebaseAuth: UserTokenGettingFirebaseAuth
) : UserTokenGettingRepository {
    override suspend fun getUserToken() =
        this.userTokenGettingFirebaseAuth.getFirebaseUserToken()
}