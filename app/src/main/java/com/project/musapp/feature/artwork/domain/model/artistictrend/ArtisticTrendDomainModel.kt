package com.project.musapp.feature.artwork.domain.model.artistictrend

import com.project.musapp.feature.artwork.presentation.model.ArtisticTrendUiModel

data class ArtisticTrendDomainModel(
    val name: String,
    val centuryRange: String,
    val artisticTrend: com.project.musapp.feature.artwork.domain.model.artistictrend.ArtisticTrend
)

fun com.project.musapp.feature.artwork.domain.model.artistictrend.ArtisticTrendDomainModel.toUiModel() =
    _root_ide_package_.com.project.musapp.feature.artwork.presentation.model.ArtisticTrendUiModel(
        name = this.name,
        centuryRange = this.centuryRange,
        artisticTrend = this.artisticTrend
    )