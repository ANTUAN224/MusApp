package com.project.musapp.feature.profile.data.di.module

import com.project.musapp.feature.profile.data.repository.UserProfileRepositoryImp
import com.project.musapp.feature.profile.domain.repository.UserProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class) //Indica que la dependencia generada tendrá el scope del ViewModel de Home.
abstract class UserProfileRepositoryModule {
    @Binds
    @ViewModelScoped //Se crea una instancia singleton para el scope del ViewModel del Home.
    abstract fun bind(homeRepositoryImp: UserProfileRepositoryImp) : UserProfileRepository
}