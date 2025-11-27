package com.project.musapp.feature.artisticculture.data.di.module

import com.project.musapp.feature.artisticculture.data.repository.ArtisticCultureRepositoryImp
import com.project.musapp.feature.artisticculture.domain.repository.ArtisticCultureRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class ArtisticCultureRepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bind(artisticCultureRepositoryImp: ArtisticCultureRepositoryImp): ArtisticCultureRepository
}