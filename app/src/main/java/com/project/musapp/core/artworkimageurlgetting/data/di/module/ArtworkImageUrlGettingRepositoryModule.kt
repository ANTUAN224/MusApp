package com.project.musapp.core.artworkimageurlgetting.data.di.module

import com.project.musapp.core.artworkimageurlgetting.data.repository.ArtworkImageUrlGettingRepositoryImp
import com.project.musapp.core.artworkimageurlgetting.domain.repository.ArtworkImageUrlGettingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ArtworkImageUrlGettingRepositoryModule {
    @Binds
    @Singleton
    abstract fun bind(
        artworkImageUrlGettingRepositoryImp: ArtworkImageUrlGettingRepositoryImp
    ): ArtworkImageUrlGettingRepository
}