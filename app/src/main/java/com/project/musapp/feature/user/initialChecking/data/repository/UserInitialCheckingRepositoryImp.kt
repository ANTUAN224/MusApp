package com.project.musapp.feature.user.initialChecking.data.repository

import com.project.musapp.feature.user.initialChecking.data.source.remote.UserInitialCheckingRemoteDataSource
import com.project.musapp.feature.user.initialChecking.domain.repository.UserInitialCheckingRepository
import javax.inject.Inject

class UserInitialCheckingRepositoryImp @Inject constructor(
    private val remoteDataSource: UserInitialCheckingRemoteDataSource
) :
    UserInitialCheckingRepository {
    override suspend fun verifyUserConnection() = remoteDataSource.verifyInternetConnection()

    override suspend fun verifyUserSession() = remoteDataSource.verifyUserSession()
}