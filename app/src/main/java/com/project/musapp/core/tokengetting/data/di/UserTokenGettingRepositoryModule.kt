package com.project.musapp.core.tokengetting.data.di

import com.project.musapp.core.tokengetting.data.repository.UserTokenGettingRepositoryImp
import com.project.musapp.core.tokengetting.domain.repository.UserTokenGettingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserTokenGettingRepositoryModule {
    @Binds
    @Singleton
    abstract fun bind(userTokenGettingRepositoryImp: UserTokenGettingRepositoryImp): UserTokenGettingRepository
}