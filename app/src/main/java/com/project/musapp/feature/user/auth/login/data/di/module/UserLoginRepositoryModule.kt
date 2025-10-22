package com.project.musapp.feature.user.auth.login.data.di.module

import com.project.musapp.feature.user.auth.login.data.repository.UserLoginRepositoryImp
import com.project.musapp.feature.user.auth.login.domain.repository.UserLoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserLoginRepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bind(userLoginRepositoryImp: UserLoginRepositoryImp) : UserLoginRepository
}