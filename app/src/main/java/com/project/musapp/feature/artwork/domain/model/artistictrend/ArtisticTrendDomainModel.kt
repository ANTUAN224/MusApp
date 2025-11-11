package com.project.musapp.feature.artwork.domain.model.artistictrend

import com.project.musapp.feature.artwork.presentation.model.ArtisticTrendUiModel

data class ArtisticTrendDomainModel(
    val name: String,
    val centuryRange: String,
    val type: ArtisticTrendType
)

fun ArtisticTrendDomainModel.toUiModel() =
    ArtisticTrendUiModel(
        name = this.name,
        centuryRange = this.centuryRange,
        type = this.type
    )