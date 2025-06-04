package com.project.musapp.feature.user.initialChecking.di.module

import com.project.musapp.feature.home.user.initialChecking.data.repository.UserInitialCheckingRepositoryImp
import com.project.musapp.feature.home.user.initialChecking.domain.repository.UserInitialCheckingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserConnectionVerificationRepositoryModule {
    @Binds //Alternativa a @Provides si la interfaz o clase abstracta sólo tiene una implementación o clase hija respectivamente.
    abstract fun bind(userRegisterRepositoryImp: UserInitialCheckingRepositoryImp): UserInitialCheckingRepository
}