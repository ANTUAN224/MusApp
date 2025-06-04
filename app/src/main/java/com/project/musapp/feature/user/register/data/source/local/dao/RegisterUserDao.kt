package com.project.musapp.feature.user.register.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import com.project.musapp.feature.user.register.data.model.local.entity.RegisterUserEntity

@Dao
interface RegisterUserDao {
    @Insert
    suspend fun insertUser(userEntity: RegisterUserEntity) : Long
}