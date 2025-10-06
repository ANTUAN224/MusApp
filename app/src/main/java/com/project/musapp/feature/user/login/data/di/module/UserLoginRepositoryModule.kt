package com.project.musapp.feature.user.login.data.di.module

import com.project.musapp.feature.user.login.data.repository.UserLoginRepositoryImp
import com.project.musapp.feature.user.login.domain.repository.UserLoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserLoginRepositoryModule {
    @Binds
    @Singleton
    abstract fun bind(userLoginRepositoryImp: UserLoginRepositoryImp) : UserLoginRepository
}