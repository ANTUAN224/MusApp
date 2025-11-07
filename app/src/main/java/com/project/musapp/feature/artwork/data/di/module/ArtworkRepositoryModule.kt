package com.project.musapp.feature.artwork.data.di.module

import com.project.musapp.feature.artwork.data.repository.ArtworkRepositoryImp
import com.project.musapp.feature.artwork.domain.repository.ArtworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ArtworkRepositoryModule {
    @Binds
    @Singleton
    abstract fun bind(artworkRepositoryImp: ArtworkRepositoryImp): ArtworkRepository
}