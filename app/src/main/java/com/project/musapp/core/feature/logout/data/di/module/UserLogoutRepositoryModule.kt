package com.project.musapp.core.feature.logout.data.di.module

import com.project.musapp.core.feature.logout.data.repository.UserLogoutRepositoryImp
import com.project.musapp.core.feature.logout.domain.repository.UserLogoutRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserLogoutRepositoryModule {
    @Binds
    @Singleton
    abstract fun bind(
        userLogoutRepositoryImpl: UserLogoutRepositoryImp
    ): UserLogoutRepository
}