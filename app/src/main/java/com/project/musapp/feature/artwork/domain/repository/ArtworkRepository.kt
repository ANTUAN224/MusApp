package com.project.musapp.feature.artwork.domain.repository

import android.net.Uri
import com.project.musapp.feature.artwork.domain.model.artwork.ArtworkDomainModel
import com.project.musapp.feature.artwork.domain.model.artwork.ArtworkPreviewDomainModel

interface ArtworkRepository {
    suspend fun getArtwork(userToken: String, artworkId: Long): ArtworkDomainModel

    suspend fun getArtworkImageUrl(artworkImagePathText: String): Uri
}