package com.project.musapp.feature.home.data.di.module

import com.project.musapp.feature.home.data.repository.HomeRepositoryImp
import com.project.musapp.feature.home.domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeRepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bind(homeRepositoryImp: HomeRepositoryImp) : HomeRepository
}