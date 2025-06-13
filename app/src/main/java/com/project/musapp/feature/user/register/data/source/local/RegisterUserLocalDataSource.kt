package com.project.musapp.feature.user.register.data.source.local

import android.content.Context
import com.project.musapp.core.database.MusAppLocalDatabase
import com.project.musapp.feature.user.register.data.model.local.dto.toLocalDTO
import com.project.musapp.feature.user.register.data.model.local.entity.toEntity
import com.project.musapp.feature.user.register.domain.model.UserRegisterModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RegisterUserLocalDataSource @Inject constructor(
    private val databaseInstance: MusAppLocalDatabase,
    @ApplicationContext private val context: Context
) {

    suspend fun insertUser(userRegisterModel: UserRegisterModel) =
        databaseInstance.getRegisterUserDao().insertUser(
            userEntity = userRegisterModel.toLocalDTO().toEntity(context = context)
        ) != 0L
}