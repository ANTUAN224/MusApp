package com.project.musapp.feature.artwork.data.model.dto.artwork

import com.project.musapp.feature.artwork.domain.model.artwork.ArtworkPreviewDomainModel

data class ArtworkPreviewDTO(
    val id: Long,
    val imagePathText: String,
    val title: String,
    val authorHistoricallyKnownName: String
)

fun ArtworkPreviewDTO.toDomainModel() =
    ArtworkPreviewDomainModel(
        id = this.id,
        imagePathText = this.imagePathText,
        title = this.title,
        authorHistoricallyKnownName = this.authorHistoricallyKnownName
    )