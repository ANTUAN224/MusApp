package com.project.musapp.feature.auth.data.di.module

import com.project.musapp.feature.auth.data.repository.UserAuthRepositoryImp
import com.project.musapp.feature.auth.domain.repository.UserAuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) //Indica que la dependencia generada tendrá el scope de la app.
abstract class UserAuthRepositoryModule {
    @Binds //Se utiliza cuando la interfaz o clase abstracta sólo tiene una implementación o clase hija respectivamente.
    @Singleton //Se crea una instancia singleton para el scope de la app.
    abstract fun bind(userAuthRepositoryImp: UserAuthRepositoryImp): UserAuthRepository
}