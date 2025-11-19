package com.project.musapp.feature.user.domain.repository

import com.project.musapp.feature.artwork.domain.model.artwork.ArtworkPreviewDomainModel
import com.project.musapp.feature.collection.domain.model.CollectionReadingDomainModel
import com.project.musapp.feature.user.domain.model.UserProfileDomainModel

interface UserRepository {
    suspend fun getUserProfile(userToken: String): UserProfileDomainModel

    suspend fun addArtworkToUserFavoriteArtworks(
        userToken: String,
        artworkId: Long
    ): List<ArtworkPreviewDomainModel>

    suspend fun deleteArtworkFromUserFavoriteArtworks(
        userToken: String,
        artworkId: Long
    ): List<ArtworkPreviewDomainModel>

    suspend fun getUserFavoriteArtworks(userToken: String): List<ArtworkPreviewDomainModel>

    suspend fun getUserCollections(userToken: String): List<CollectionReadingDomainModel>
}