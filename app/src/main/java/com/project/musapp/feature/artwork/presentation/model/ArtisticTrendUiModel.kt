package com.project.musapp.feature.artwork.presentation.model

import com.project.musapp.feature.artwork.domain.model.artistictrend.ArtisticTrend

data class ArtisticTrendUiModel(
    val name: String,
    val centuryRange: String,
    val artisticTrend: com.project.musapp.feature.artwork.domain.model.artistictrend.ArtisticTrend
)