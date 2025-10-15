package com.project.musapp.feature.user.registration.data.di.module

import com.project.musapp.feature.user.registration.data.repository.UserRegistrationRepositoryImp
import com.project.musapp.feature.user.registration.domain.repository.UserRegistrationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class) //Indica que la dependencia generada será en el scope del ViewModel.
abstract class UserRegistrationRepositoryModule {
    @Binds //Se utiliza cuando la interfaz o clase abstracta sólo tiene una implementación o clase hija respectivamente.
    @ViewModelScoped //Se crea una instancia singleton para el scope del ViewModel del registro del usuario.
    abstract fun bind(userRegistrationRepositoryImp: UserRegistrationRepositoryImp): UserRegistrationRepository
}