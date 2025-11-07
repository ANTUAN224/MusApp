package com.project.musapp.feature.artwork.data.model.dto

import com.project.musapp.feature.artwork.domain.model.artistictrend.ArtisticTrend
import com.project.musapp.feature.artwork.domain.model.artistictrend.ArtisticTrendDomainModel

data class ArtisticTrendDTO(
    val name: String,
    val centuryRange: String,
    val artisticTrend: com.project.musapp.feature.artwork.domain.model.artistictrend.ArtisticTrend
)

fun com.project.musapp.feature.artwork.data.model.dto.ArtisticTrendDTO.toDomainModel() =
    _root_ide_package_.com.project.musapp.feature.artwork.domain.model.artistictrend.ArtisticTrendDomainModel(
        name = this.name,
        centuryRange = this.centuryRange,
        artisticTrend = this.artisticTrend
    )