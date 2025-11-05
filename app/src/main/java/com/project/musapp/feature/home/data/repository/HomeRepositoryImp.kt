package com.project.musapp.feature.home.data.repository

import android.net.Uri
import com.google.firebase.storage.StorageException
import com.project.musapp.core.internetconnectionverification.domain.exception.InternetConnectionVerificationException
import com.project.musapp.feature.home.data.model.dto.toDomainModel
import com.project.musapp.feature.home.data.source.remote.ArtworkImageUrlGettingFirebaseStorage
import com.project.musapp.feature.home.data.source.remote.UserFavoriteArtworksGettingRetrofit
import com.project.musapp.feature.home.data.source.remote.UserProfileGettingRetrofit
import com.project.musapp.feature.home.domain.model.ArtworkPreviewDomainModel
import com.project.musapp.feature.home.domain.model.UserProfileDomainModel
import com.project.musapp.feature.home.domain.repository.HomeRepository
import java.io.IOException
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(
    private val userProfileGettingRetrofit: UserProfileGettingRetrofit,
    private val userFavoriteArtworksGettingRetrofit: UserFavoriteArtworksGettingRetrofit,
    private val artworkImageUrlGettingFirebaseStorage: ArtworkImageUrlGettingFirebaseStorage
) : HomeRepository {
    override suspend fun getUserProfile(userToken: String): UserProfileDomainModel {
        try {
            return userProfileGettingRetrofit.getUserProfile(
                firebaseUserToken = userToken
            ).toDomainModel()
        } catch (_: IOException) {
            throw InternetConnectionVerificationException.NoInternetConnectionException
        }
    }

    override suspend fun getUserFavoriteArtworks(userToken: String): List<ArtworkPreviewDomainModel> {
        try {
            return userFavoriteArtworksGettingRetrofit.getUserFavoriteArtworks(userToken = userToken)
                .map { artworkPreviewDTO -> artworkPreviewDTO.toDomainModel() }
        } catch (_: IOException) {
            throw InternetConnectionVerificationException.NoInternetConnectionException
        }
    }

    override suspend fun getArtworkImageUrl(artworkImagePathText: String): Uri {
        try {
            return artworkImageUrlGettingFirebaseStorage.getArtworkImageUrl(artworkImagePath = artworkImagePathText)
        } catch (_: StorageException) {
            throw InternetConnectionVerificationException.NoInternetConnectionException
        }
    }

    override suspend fun getSearchArtworks() {
        TODO("Not yet implemented")
    }

}