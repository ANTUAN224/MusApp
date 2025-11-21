package com.project.musapp.feature.user.data.repository

import com.project.musapp.core.network.domain.exception.NetworkException
import com.project.musapp.feature.artwork.data.model.dto.artwork.toDomainModel
import com.project.musapp.feature.artwork.domain.model.artwork.ArtworkPreviewDomainModel
import com.project.musapp.feature.collection.data.model.dto.toDomainModel
import com.project.musapp.feature.collection.domain.model.CollectionReadingDomainModel
import com.project.musapp.feature.user.data.model.dto.toDomainModel
import com.project.musapp.feature.user.data.source.remote.api.UserHttpRequestRetrofit
import com.project.musapp.feature.user.domain.model.UserProfileDomainModel
import com.project.musapp.feature.user.domain.repository.UserRepository
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(
    private val userHttpRequestRetrofit: UserHttpRequestRetrofit,
) : UserRepository {
    override suspend fun getUserProfile(userToken: String): UserProfileDomainModel {
        try {
            return userHttpRequestRetrofit.getUserProfile(
                userToken = userToken
            ).toDomainModel()
        } catch (_: IOException) {
            throw NetworkException.NoInternetConnectionException
        }
    }

    override suspend fun addArtworkToUserFavoriteArtworks(
        userToken: String,
        artworkId: Long
    ): List<ArtworkPreviewDomainModel> {
        try {
            return userHttpRequestRetrofit.addArtworkToUserFavoriteArtworks(
                userToken = userToken,
                artworkId = artworkId
            ).map { artworkPreviewDTO -> artworkPreviewDTO.toDomainModel() }
        } catch (_: IOException) {
            throw NetworkException.NoInternetConnectionException
        }
    }

    override suspend fun deleteArtworkFromUserFavoriteArtworks(
        userToken: String,
        artworkId: Long
    ): List<ArtworkPreviewDomainModel> {
        try {
            return userHttpRequestRetrofit.deleteArtworkFromUserFavoriteArtworks(
                userToken = userToken,
                artworkId = artworkId
            ).map { artworkPreviewDTO -> artworkPreviewDTO.toDomainModel() }
        } catch (_: IOException) {
            throw NetworkException.NoInternetConnectionException
        }
    }
}