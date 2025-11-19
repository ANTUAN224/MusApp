package com.project.musapp.feature.user.data.di.module

import com.project.musapp.feature.user.data.repository.UserRepositoryImp
import com.project.musapp.feature.user.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRepositoryModule {
    @Binds
    @Singleton
    abstract fun bind(userRepositoryImp: UserRepositoryImp) : UserRepository
}