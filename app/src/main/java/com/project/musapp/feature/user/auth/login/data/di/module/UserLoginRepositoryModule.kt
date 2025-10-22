package com.project.musapp.feature.user.auth.login.data.di.module

import com.project.musapp.feature.user.auth.login.data.repository.UserLoginRepositoryImp
import com.project.musapp.feature.user.auth.login.domain.repository.UserLoginRepository
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