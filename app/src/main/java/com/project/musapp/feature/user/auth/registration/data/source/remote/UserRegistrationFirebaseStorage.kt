package com.project.musapp.feature.user.auth.registration.data.source.remote

import android.content.Context
import android.net.Uri
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageException
import com.google.firebase.storage.ktx.storage
import com.project.musapp.core.internetconnectionverification.domain.exception.NoInternetConnectionException
import com.project.musapp.feature.user.auth.registration.data.helper.ImageConversionHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRegistrationFirebaseStorage @Inject constructor(
    @ApplicationContext private val context: Context
) {
    suspend fun uploadUserProfileImage(profileImageLocalPath: Uri): String {
        Firebase.storage.maxUploadRetryTimeMillis = 5000

        Firebase.storage.maxDownloadRetryTimeMillis = 5000

        val imageData = ImageConversionHelper.toByteArray(
            context = context,
            imagePath = profileImageLocalPath
        )

        try {
            val storageRef =
                Firebase.storage.reference.child(
                    "profile-pictures/${Firebase.auth.currentUser!!.uid}"
                )

            val uploadTask = storageRef.putBytes(imageData).await()

            val userProfileImageUrl = uploadTask.storage.downloadUrl.await()

            return userProfileImageUrl.toString()
        } catch (_: StorageException) {
            throw NoInternetConnectionException()
        }
    }
}