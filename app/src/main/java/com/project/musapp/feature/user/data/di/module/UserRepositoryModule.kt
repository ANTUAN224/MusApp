package com.project.musapp.feature.user.data.di.module

import com.project.musapp.feature.user.data.repository.UserRepositoryImp
import com.project.musapp.feature.user.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class) //Indica que la dependencia generada tendrá el scope del ViewModel de Home.
abstract class UserRepositoryModule {
    @Binds
    @ViewModelScoped //Se crea una instancia singleton para el scope del ViewModel del Home.
    abstract fun bind(homeRepositoryImp: UserRepositoryImp) : UserRepository
}