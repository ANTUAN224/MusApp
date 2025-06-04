package com.project.musapp.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.project.musapp.feature.user.register.data.model.local.entity.RegisterUserEntity
import com.project.musapp.feature.user.register.data.source.local.dao.RegisterUserDao

@Database(entities = [RegisterUserEntity::class], version = 1, exportSchema = false)
abstract class MusAppLocalDatabase : RoomDatabase() {
    abstract fun getRegisterUserDao(): RegisterUserDao
//    abstract fun getLoginUserDao(): LoginUserDao
//abstract fun getArtworkDao() : ArtworkDao
//abstract fun getCollectionDao() : CollectionDao
}