package com.project.musapp.feature.user.register.data.source.remote

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.project.musapp.core.helper.ImageConversionHelper
import com.project.musapp.feature.user.register.data.source.remote.client.RegisterUserApiClient
import com.project.musapp.feature.user.register.domain.model.RegisterUserModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RegisterUserRemoteDataSource @Inject constructor(
    private val registerUserApiClient: RegisterUserApiClient,
    @ApplicationContext private val context: Context
) {
    suspend fun createUserInFirebase(email: String, password: String): Boolean {
        return try {
            val authResult =
                Firebase.auth.createUserWithEmailAndPassword(email, password).await()

            authResult.user != null
        } catch (_: FirebaseAuthException) {
            false
        } catch (_: Exception) {
            false
        }
    }

    private suspend fun getFirebaseUserToken(): String {
        val firebaseUserTokenResult =
            Firebase.auth.currentUser!!.getIdToken(true).await()

        return firebaseUserTokenResult.token.toString()
    }

    private suspend fun uploadUserProfileImageToFirebaseStorage(imageData: ByteArray): String {
        val storageRef =
            Firebase.storage.reference.child("profile-pictures/${FirebaseAuth.getInstance().currentUser!!.uid}")

        val uploadTask = storageRef.putBytes(imageData).await()

        val userProfileImageUrl = uploadTask.storage.downloadUrl.await()

        return userProfileImageUrl.toString()
    }

//    suspend fun insertUser(registerUserModel: RegisterUserModel): Boolean {
//        return registerUserApiClient
//            .insertUser(
//                headerCompanionValue = "Bearer ${getFirebaseUserToken()}",
//                registerUserRemoteDTO = registerUserModel.toRemoteDTO(
//                    userProfileImageUrl =
//                        uploadUserProfileImageToFirebaseStorage(
//                            imageData = ImageConversionHelper.toByteArray(
//                                context = context,
//                                imagePath = registerUserModel.imagePath
//                            )
//                        )
//                )
//            ).isSuccessful
//    }
}