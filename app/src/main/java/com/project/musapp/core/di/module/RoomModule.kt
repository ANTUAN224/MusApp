package com.project.musapp.core.di.module

import android.content.Context
import androidx.room.Room
import com.project.musapp.core.database.MusAppLocalDatabase
import com.project.musapp.feature.user.register.data.source.local.dao.RegisterUserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    const val DB_NAME = "musapp_database"

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context): MusAppLocalDatabase =
        Room.databaseBuilder(context = context, MusAppLocalDatabase::class.java, DB_NAME).build()

    @Provides
    @Singleton
    fun provideUserRegisterUserDao(localDatabase: MusAppLocalDatabase): RegisterUserDao =
        localDatabase.getRegisterUserDao()
}