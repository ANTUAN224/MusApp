package com.project.musapp.feature.home.presentation.model

import android.net.Uri

data class ArtworkPreviewUiModel(
    val id: Long,
    val imageUrl: Uri,
    val title: String,
    val authorHistoricallyKnownName: String
)

fun List<ArtworkPreviewUiModel>.chunkInPairs(): List<Pair<ArtworkPreviewUiModel, ArtworkPreviewUiModel?>> {
    val chunkedList = mutableListOf<Pair<ArtworkPreviewUiModel, ArtworkPreviewUiModel?>>()
    var index = 0

    do {
        chunkedList.add(
            Pair(
                this[index],
                if (index + 1 < this.size) this[index + 1] else null
            )
        )

        index += 2
    } while (index < this.size)

    return chunkedList
}