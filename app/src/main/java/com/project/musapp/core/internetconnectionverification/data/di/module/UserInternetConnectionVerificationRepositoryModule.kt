package com.project.musapp.core.internetconnectionverification.data.di.module

import com.project.musapp.core.internetconnectionverification.data.repository.UserInternetConnectionVerificationRepositoryImp
import com.project.musapp.core.internetconnectionverification.domain.repository.UserInternetConnectionVerificationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserInternetConnectionVerificationRepositoryModule {
    @Binds //Alternativa a @Provides si la interfaz o clase abstracta sólo tiene una implementación o clase hija respectivamente.
    @Singleton
    abstract fun bind(userInternetConnectionVerificationRepositoryImp: UserInternetConnectionVerificationRepositoryImp): UserInternetConnectionVerificationRepository
}