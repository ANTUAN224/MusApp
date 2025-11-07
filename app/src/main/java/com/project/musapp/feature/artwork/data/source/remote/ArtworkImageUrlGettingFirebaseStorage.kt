package com.project.musapp.feature.artwork.data.source.remote

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ArtworkImageUrlGettingFirebaseStorage @Inject constructor() {
    suspend fun getArtworkImageUrl(artworkImagePath: String): Uri {
        Firebase.storage.maxDownloadRetryTimeMillis = 5000

        val storageRef = Firebase.storage.reference

        val fileRef = storageRef.child(artworkImagePath)

        return fileRef.downloadUrl.await()
    }
}