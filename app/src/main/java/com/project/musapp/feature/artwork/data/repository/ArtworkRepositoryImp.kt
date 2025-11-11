package com.project.musapp.feature.artwork.data.repository

import android.net.Uri
import com.google.firebase.storage.StorageException
import com.project.musapp.core.network.domain.exception.NetworkException
import com.project.musapp.feature.artwork.data.model.dto.artwork.toDomainModel
import com.project.musapp.feature.artwork.data.source.remote.ArtworkImageUrlGettingFirebaseStorage
import com.project.musapp.feature.artwork.data.source.remote.api.ArtworkGettingRetrofit
import com.project.musapp.feature.artwork.domain.model.artwork.ArtworkDomainModel
import com.project.musapp.feature.artwork.domain.repository.ArtworkRepository
import java.io.IOException
import javax.inject.Inject

class ArtworkRepositoryImp @Inject constructor(
    private val artworkImageUrlGettingFirebaseStorage: ArtworkImageUrlGettingFirebaseStorage,
    private val artworkGettingRetrofit: ArtworkGettingRetrofit
) :
    ArtworkRepository {
    override suspend fun getArtwork(
        userToken: String,
        artworkId: Long
    ): ArtworkDomainModel {
        try {
            return artworkGettingRetrofit.getArtwork(userToken = userToken, artworkId = artworkId)
                .toDomainModel()
        } catch (_: IOException) {
            throw NetworkException.NoInternetConnectionException
        }
    }

    override suspend fun getArtworkImageUrl(artworkImagePathText: String): Uri {
        try {
            return artworkImageUrlGettingFirebaseStorage.getArtworkImageUrl(artworkImagePath = artworkImagePathText)
        } catch (_: StorageException) {
            throw NetworkException.NoInternetConnectionException
        }
    }
}