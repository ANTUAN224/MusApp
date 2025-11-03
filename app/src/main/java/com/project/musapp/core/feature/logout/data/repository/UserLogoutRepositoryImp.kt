package com.project.musapp.core.feature.logout.data.repository

import com.project.musapp.core.feature.logout.data.source.local.UserLogoutFirebaseAuth
import com.project.musapp.core.feature.logout.domain.repository.UserLogoutRepository
import javax.inject.Inject

class UserLogoutRepositoryImp @Inject constructor(
    private val userLogoutFirebaseAuth: UserLogoutFirebaseAuth
) : UserLogoutRepository {
    override fun logOutUser() {
        this.userLogoutFirebaseAuth.logOutUser()
    }
}