package com.project.musapp.feature.artwork.data.model.dto

import com.project.musapp.feature.artwork.domain.model.artistictrend.ArtisticTrendDomainModel
import com.project.musapp.feature.artwork.domain.model.artistictrend.ArtisticTrendType

data class ArtisticTrendDTO(
    val name: String,
    val centuryRange: String,
    val type: ArtisticTrendType
)

fun ArtisticTrendDTO.toDomainModel() =
    ArtisticTrendDomainModel(
        name = this.name,
        centuryRange = this.centuryRange,
        type = this.type
    )