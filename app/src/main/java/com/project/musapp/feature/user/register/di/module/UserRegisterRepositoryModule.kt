package com.project.musapp.feature.user.register.di.module

import com.project.musapp.feature.user.register.data.repository.UserRegisterRepositoryImp
import com.project.musapp.feature.user.register.domain.repository.UserRegisterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserRegisterRepositoryModule {
    @Binds //Alternativa a @Provides si la interfaz o clase abstracta sólo tiene una implementación o clase hija respectivamente.
    abstract fun bind(userRegisterRepositoryImp: UserRegisterRepositoryImp): UserRegisterRepository
}