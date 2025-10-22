package com.project.musapp.core.sessionstateverification.data.di

import com.project.musapp.core.sessionstateverification.data.repository.UserSessionStateVerificationRepositoryImp
import com.project.musapp.core.sessionstateverification.domain.repository.UserSessionStateVerificationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserSessionStateVerificationRepositoryModule {
    @Binds
    @Singleton
    abstract fun bind(userSessionVerificationRepositoryImp: UserSessionStateVerificationRepositoryImp)
            : UserSessionStateVerificationRepository
}