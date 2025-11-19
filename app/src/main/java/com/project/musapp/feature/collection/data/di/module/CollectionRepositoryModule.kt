package com.project.musapp.feature.collection.data.di.module

import com.project.musapp.feature.collection.data.repository.CollectionRepositoryImp
import com.project.musapp.feature.collection.domain.repository.CollectionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CollectionRepositoryModule {
    @Binds
    @Singleton
    abstract fun bind(collectionRepositoryImp: CollectionRepositoryImp): CollectionRepository
}