package com.project.musapp.feature.home.user.profile.data.source.local.userDao

import androidx.room.Dao
import androidx.room.Insert
import com.project.musapp.feature.user.register.data.model.local.entity.RegisterUserEntity

@Dao
interface RegisterUserDao {
    @Insert
    suspend fun insertUser(userEntity: RegisterUserEntity) : Long
}