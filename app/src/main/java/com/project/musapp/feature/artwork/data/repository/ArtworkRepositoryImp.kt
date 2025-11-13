package com.project.musapp.feature.artwork.data.repository

import com.project.musapp.core.network.domain.exception.NetworkException
import com.project.musapp.feature.artwork.data.model.dto.artwork.toDomainModel
import com.project.musapp.feature.artwork.data.source.remote.api.ArtworkGettingRetrofit
import com.project.musapp.feature.artwork.domain.model.artwork.ArtworkDomainModel
import com.project.musapp.feature.artwork.domain.model.artwork.ArtworkPreviewDomainModel
import com.project.musapp.feature.artwork.domain.repository.ArtworkRepository
import java.io.IOException
import javax.inject.Inject

class ArtworkRepositoryImp @Inject constructor(
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

    override suspend fun getSearchArtworks(userToken: String): List<ArtworkPreviewDomainModel> {
        try {
            return artworkGettingRetrofit.getSearchArtworks(userToken = userToken)
                .map { artworkPreviewDTO -> artworkPreviewDTO.toDomainModel() }
        } catch (_: IOException) {
            throw NetworkException.NoInternetConnectionException
        }
    }
}