package com.project.musapp.feature.artwork.presentation.model.artwork

import android.net.Uri
import com.project.musapp.feature.artwork.presentation.model.ArtisticTrendUiModel
import com.project.musapp.feature.artwork.presentation.model.AuthorUiModel
import com.project.musapp.feature.artwork.presentation.model.LocationUiModel

data class ArtworkUiModel(
    val id: Long,
    val title: String,
    val description: String,
    val imageUrl: Uri,
    val historicalContext: String,
    val technique: String,
    val support: String,
    val dimensions: String,
    val author: AuthorUiModel,
    val culturalEra: String,
    val artisticTrend: ArtisticTrendUiModel,
    val location: LocationUiModel,
    val hasBeenMarkedAsFavorite: Boolean
)