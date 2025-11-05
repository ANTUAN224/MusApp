package com.project.musapp.feature.home.domain.repository

import android.net.Uri
import com.project.musapp.feature.home.domain.model.ArtworkPreviewDomainModel
import com.project.musapp.feature.home.domain.model.UserProfileDomainModel

interface HomeRepository {
    suspend fun getUserProfile(userToken: String) : UserProfileDomainModel

    suspend fun getUserFavoriteArtworks(userToken: String): List<ArtworkPreviewDomainModel>

    suspend fun getArtworkImageUrl(artworkImagePathText: String): Uri

    suspend fun getSearchArtworks()
}