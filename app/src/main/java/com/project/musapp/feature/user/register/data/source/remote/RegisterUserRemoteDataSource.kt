package com.project.musapp.feature.user.register.data.source.remote

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.project.musapp.feature.user.register.data.model.remote.dto.toRemoteDTO
import com.project.musapp.feature.user.register.data.source.remote.service.RegisterUserApiService
import com.project.musapp.feature.user.register.domain.model.UserRegisterModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RegisterUserRemoteDataSource @Inject constructor(
   private val registerUserApiService: RegisterUserApiService,
    @ApplicationContext private val context: Context
) {
    suspend fun createUserInFirebaseAuth(email: String, password: String): Boolean {
        var result = false

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { operationResult ->
                result = operationResult.isSuccessful
            }

        return result
    }

    suspend fun insertUser(userRegisterModel: UserRegisterModel): Boolean {
        return registerUserApiService
                .insertUser(userRegisterModel.toRemoteDTO(context = context)).isSuccessful
        return true
    }
}