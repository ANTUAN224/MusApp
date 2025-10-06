package com.project.musapp.feature.user.initialchecking.data.repository

import com.project.musapp.feature.user.initialchecking.data.source.remote.UserInitialCheckingRemoteDataSource
import com.project.musapp.feature.user.initialchecking.domain.repository.UserInitialCheckingRepository
import javax.inject.Inject

class UserInitialCheckingRepositoryImp @Inject constructor(
    private val remoteDataSource: UserInitialCheckingRemoteDataSource
) :
    UserInitialCheckingRepository {
    override fun verifyUserConnection() = remoteDataSource.verifyInternetConnection()

    override fun verifyUserSession() = remoteDataSource.verifyUserSession()
}