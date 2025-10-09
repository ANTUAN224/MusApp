package com.project.musapp.feature.user.initialchecking.data.repository

import com.project.musapp.feature.user.initialchecking.data.source.remote.UserStateInitialCheckingRemoteDataSource
import com.project.musapp.feature.user.initialchecking.domain.repository.UserStateInitialCheckingRepository
import javax.inject.Inject

class UserStateStateInitialCheckingRepositoryImp @Inject constructor(
    private val remoteDataSource: UserStateInitialCheckingRemoteDataSource
) :
    UserStateInitialCheckingRepository {
    override fun verifyUserConnection() = remoteDataSource.verifyInternetConnection()

    override fun verifyUserSession() = remoteDataSource.verifyUserSession()
}