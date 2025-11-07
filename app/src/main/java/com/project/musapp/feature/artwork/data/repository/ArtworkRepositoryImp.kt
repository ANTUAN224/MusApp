package com.project.musapp.feature.artwork.data.repository

import android.net.Uri
import com.google.firebase.storage.StorageException
import com.project.musapp.core.network.domain.exception.NetworkException
import com.project.musapp.feature.artwork.data.model.dto.artwork.toDomainModel
import com.project.musapp.feature.artwork.data.source.remote.ArtworkImageUrlGettingFirebaseStorage
import com.project.musapp.feature.artwork.domain.model.artwork.ArtworkDomainModel
import com.project.musapp.feature.artwork.domain.model.artwork.ArtworkPreviewDomainModel
import com.project.musapp.feature.artwork.domain.repository.ArtworkRepository
import com.project.musapp.feature.artwork.data.source.remote.api.UserFavoriteArtworksGettingRetrofit
import java.io.IOException
import javax.inject.Inject

class ArtworkRepositoryImp @Inject constructor(
    private val artworkImageUrlGettingFirebaseStorage: ArtworkImageUrlGettingFirebaseStorage,
    private val userFavoriteArtworksGettingRetrofit: UserFavoriteArtworksGettingRetrofit
) :
    ArtworkRepository {
    override suspend fun getArtworkInformation(userToken: String): ArtworkDomainModel {
        TODO("Not yet implemented")
    }

    override suspend fun addArtworkToCollections(userToken: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteArtworkFromCollections(userToken: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getArtworkImageUrl(artworkImagePathText: String): Uri {
        try {
            return artworkImageUrlGettingFirebaseStorage.getArtworkImageUrl(artworkImagePath = artworkImagePathText)
        } catch (_: StorageException) {
            throw NetworkException.NoInternetConnectionException
        }
    }

    override suspend fun getUserFavoriteArtworks(userToken: String): List<ArtworkPreviewDomainModel> {
        try {
            return userFavoriteArtworksGettingRetrofit.getUserFavoriteArtworks(userToken = userToken)
                .map { artworkPreviewDTO -> artworkPreviewDTO.toDomainModel() }
        } catch (_: IOException) {
            throw NetworkException.NoInternetConnectionException
        }
    }
}