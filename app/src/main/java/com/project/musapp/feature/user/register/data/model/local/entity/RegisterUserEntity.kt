package com.project.musapp.feature.user.register.data.model.local.entity

import android.content.Context
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.project.musapp.core.helper.ImageConversionHelper
import com.project.musapp.feature.user.register.data.model.local.dto.RegisterUserLocalDTO
import com.project.musapp.feature.user.register.domain.model.UserRegisterModel

@Entity(tableName = "users")
data class RegisterUserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "surnames") val surnames: String,
    @ColumnInfo(name = "birthdate") val birthdate: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "image") val image: ByteArray
)

fun RegisterUserLocalDTO.toEntity(context: Context) = RegisterUserEntity(
    name = name,
    surnames = surnames,
    birthdate = birthdate.toString(),
    email = email,
    image = ImageConversionHelper.toByteArray(context = context, imagePath = imagePath),
)
