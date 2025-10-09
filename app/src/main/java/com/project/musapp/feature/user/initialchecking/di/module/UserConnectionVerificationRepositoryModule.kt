package com.project.musapp.feature.user.initialchecking.di.module

import com.project.musapp.feature.user.initialchecking.data.repository.UserStateStateInitialCheckingRepositoryImp
import com.project.musapp.feature.user.initialchecking.domain.repository.UserStateInitialCheckingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserConnectionVerificationRepositoryModule {
    @Binds //Alternativa a @Provides si la interfaz o clase abstracta sólo tiene una implementación o clase hija respectivamente.
    @Singleton
    abstract fun bind(userRegisterRepositoryImp: UserStateStateInitialCheckingRepositoryImp): UserStateInitialCheckingRepository
}