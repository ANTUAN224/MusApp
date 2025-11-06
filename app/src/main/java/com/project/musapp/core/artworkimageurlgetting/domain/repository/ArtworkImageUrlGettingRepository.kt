package com.project.musapp.core.artworkimageurlgetting.domain.repository

import android.net.Uri

interface ArtworkImageUrlGettingRepository {
    suspend fun getArtworkImageUrl(artworkImagePathText: String): Uri
}