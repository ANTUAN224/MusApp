package com.project.musapp.feature.artwork.data.model.dto.artwork

import androidx.core.net.toUri
import com.project.musapp.feature.artwork.domain.model.artwork.ArtworkPreviewDomainModel

data class ArtworkPreviewDTO(
    val id: Long,
    val imageUrlText: String,
    val title: String,
    val authorHistoricallyKnownName: String
)

fun ArtworkPreviewDTO.toDomainModel() =
    ArtworkPreviewDomainModel(
        id = this.id,
        imageUrl = this.imageUrlText.toUri(),
        title = this.title,
        authorHistoricallyKnownName = this.authorHistoricallyKnownName
    )