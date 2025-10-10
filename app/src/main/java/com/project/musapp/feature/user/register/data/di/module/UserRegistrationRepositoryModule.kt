package com.project.musapp.feature.user.register.data.di.module

import com.project.musapp.feature.user.register.data.repository.UserRegistrationRepositoryImp
import com.project.musapp.feature.user.register.domain.repository.UserRegisterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRegistrationRepositoryModule {
    @Binds //Se utiliza cuando la interfaz o clase abstracta sólo tiene una implementación o clase hija respectivamente.
    @Singleton
    abstract fun bind(userRegistrationRepositoryImp: UserRegistrationRepositoryImp): UserRegisterRepository
}