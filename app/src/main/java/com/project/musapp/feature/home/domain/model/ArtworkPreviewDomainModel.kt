package com.project.musapp.feature.home.domain.model

import android.net.Uri
import com.project.musapp.feature.home.presentation.model.ArtworkPreviewUiModel

data class ArtworkPreviewDomainModel(
    val id: Long,
    val imagePathText: String,
    val title: String,
    val authorHistoricallyKnownName: String
)

fun ArtworkPreviewDomainModel.toUiModel(imageUrl: Uri) =
    ArtworkPreviewUiModel(
        id = this.id,
        imageUrl = imageUrl,
        title = this.title,
        authorHistoricallyKnownName = this.authorHistoricallyKnownName
    )