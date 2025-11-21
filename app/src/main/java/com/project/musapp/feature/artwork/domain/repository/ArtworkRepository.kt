package com.project.musapp.feature.artwork.domain.repository

import com.project.musapp.feature.artwork.domain.model.artwork.ArtworkDomainModel
import com.project.musapp.feature.artwork.domain.model.artwork.ArtworkPreviewDomainModel

interface ArtworkRepository {
    suspend fun getUserFavoriteArtworks(userToken: String): List<ArtworkPreviewDomainModel>

    suspend fun getArtwork(userToken: String, artworkId: Long): ArtworkDomainModel

    suspend fun getSearchArtworks(userToken: String): List<ArtworkPreviewDomainModel>
}