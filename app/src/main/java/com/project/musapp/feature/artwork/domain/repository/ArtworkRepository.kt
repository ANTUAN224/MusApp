package com.project.musapp.feature.artwork.domain.repository

import android.net.Uri
import com.project.musapp.feature.artwork.domain.model.artwork.ArtworkDomainModel
import com.project.musapp.feature.artwork.domain.model.artwork.ArtworkPreviewDomainModel

interface ArtworkRepository {
    suspend fun getArtworkInformation(userToken: String): ArtworkDomainModel

    suspend fun addArtworkToCollections(userToken: String)

    suspend fun deleteArtworkFromCollections(userToken: String)

    suspend fun getArtworkImageUrl(artworkImagePathText: String): Uri

    suspend fun getUserFavoriteArtworks(userToken: String): List<ArtworkPreviewDomainModel>
}