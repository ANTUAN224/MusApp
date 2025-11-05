package com.project.musapp.feature.home.data.model.dto

import com.project.musapp.feature.home.domain.model.ArtworkPreviewDomainModel

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