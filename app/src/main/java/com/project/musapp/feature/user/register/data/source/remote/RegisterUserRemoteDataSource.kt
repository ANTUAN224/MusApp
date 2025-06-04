package com.project.musapp.feature.user.register.data.source.remote

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.storage.FirebaseStorage
import com.project.musapp.core.helper.ImageConversionHelper
import com.project.musapp.feature.user.register.data.model.remote.dto.toRemoteDTO
import com.project.musapp.feature.user.register.data.source.remote.client.UserRegisterApiClient
import com.project.musapp.feature.user.register.domain.model.RegisterUserModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RegisterUserRemoteDataSource @Inject constructor(
    private val userRegisterApiClient: UserRegisterApiClient,
    @ApplicationContext private val context: Context
) {
    suspend fun createUserInFirebase(email: String, password: String): Boolean {
        return try {
            val authResult =
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).await()

            authResult.user != null
        } catch (_: FirebaseAuthException) {
            false
        } catch (_: Exception) {
            false
        }
    }

    private suspend fun getFirebaseUserToken(): String {
        val firebaseUserTokenResult =
            FirebaseAuth.getInstance().currentUser!!.getIdToken(true).await()

        return firebaseUserTokenResult.token.toString()
    }

    private suspend fun uploadUserProfileImageToFirebaseStorage(imageData: ByteArray): String {
        val storageRef =
            FirebaseStorage.getInstance().reference.child("profile-pictures/${FirebaseAuth.getInstance().currentUser!!.uid}")

        val uploadTask = storageRef.putBytes(imageData).await()

        val userProfileImageUrl = uploadTask.storage.downloadUrl.await()

        return userProfileImageUrl.toString()
    }

    suspend fun insertUser(userRegisterModel: RegisterUserModel): Boolean {
        return userRegisterApiClient
            .insertUser(
                headerCompanionValue = "Bearer ${getFirebaseUserToken()}",
                registerUserRemoteDTO = userRegisterModel.toRemoteDTO(
                    userProfileImageUrl =
                        uploadUserProfileImageToFirebaseStorage(
                            ImageConversionHelper.toByteArray(
                                context = context,
                                imagePath = userRegisterModel.imagePath
                            )
                        )
                )
            ).isSuccessful
    }
}