package com.project.musapp.feature.user.register.di.module

import com.project.musapp.feature.user.register.data.repository.RegisterUserRepositoryImp
import com.project.musapp.feature.user.register.domain.repository.UserRegisterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRegisterRepositoryModule {
    @Binds //Alternativa a @Provides si la interfaz o clase abstracta sólo tiene una implementación o clase hija respectivamente.
    @Singleton
    abstract fun bind(registerUserRepositoryImp: RegisterUserRepositoryImp): UserRegisterRepository
}