package com.project.musapp.feature.artwork.domain.model.artwork

import android.net.Uri
import com.project.musapp.feature.artwork.presentation.model.artwork.ArtworkPreviewUiModel

data class ArtworkPreviewDomainModel(
    val id: Long,
    val imageUrl: Uri,
    val title: String,
    val authorHistoricallyKnownName: String
)

fun ArtworkPreviewDomainModel.toUiModel() =
    ArtworkPreviewUiModel(
        id = this.id,
        imageUrl = this.imageUrl,
        title = this.title,
        authorHistoricallyKnownName = this.authorHistoricallyKnownName
    )