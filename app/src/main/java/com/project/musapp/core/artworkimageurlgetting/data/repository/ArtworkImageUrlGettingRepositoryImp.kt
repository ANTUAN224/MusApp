package com.project.musapp.core.artworkimageurlgetting.data.repository

import android.net.Uri
import com.google.firebase.storage.StorageException
import com.project.musapp.core.artworkimageurlgetting.data.source.remote.ArtworkImageUrlGettingFirebaseStorage
import com.project.musapp.core.artworkimageurlgetting.domain.repository.ArtworkImageUrlGettingRepository
import com.project.musapp.core.internetconnectionverification.domain.exception.InternetConnectionVerificationException
import javax.inject.Inject

class ArtworkImageUrlGettingRepositoryImp @Inject constructor(
    private val artworkImageUrlGettingFirebaseStorage: ArtworkImageUrlGettingFirebaseStorage
) :
    ArtworkImageUrlGettingRepository {
    override suspend fun getArtworkImageUrl(artworkImagePathText: String): Uri {
        try {
            return artworkImageUrlGettingFirebaseStorage.getArtworkImageUrl(artworkImagePath = artworkImagePathText)
        } catch (_: StorageException) {
            throw InternetConnectionVerificationException.NoInternetConnectionException
        }
    }
}