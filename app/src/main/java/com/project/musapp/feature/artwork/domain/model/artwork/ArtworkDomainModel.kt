package com.project.musapp.feature.artwork.domain.model.artwork

import android.net.Uri
import com.project.musapp.feature.artwork.domain.model.LocationDomainModel
import com.project.musapp.feature.artwork.domain.model.artistictrend.ArtisticTrendDomainModel
import com.project.musapp.feature.artwork.domain.model.artistictrend.toUiModel
import com.project.musapp.feature.artwork.domain.model.author.AuthorDomainModel
import com.project.musapp.feature.artwork.domain.model.author.toUiModel
import com.project.musapp.feature.artwork.domain.model.toUiModel
import com.project.musapp.feature.artwork.presentation.model.artwork.ArtworkUiModel

data class ArtworkDomainModel(
    val id: Long,
    val title: String,
    val description: String,
    val imagePathText: String,
    val historicalContext: String,
    val technique: String,
    val support: String,
    val dimensions: String,
    val author: AuthorDomainModel,
    val culturalEra: String,
    val artisticTrend: ArtisticTrendDomainModel,
    val location: LocationDomainModel,
    val hasBeenMarkedAsFavorite: Boolean
)

suspend fun ArtworkDomainModel.toUiModel(onImagePathProvision: suspend (String) -> Uri) =
    ArtworkUiModel(
        id = this.id,
        title = this.title,
        description = this.description,
        imageUrl = onImagePathProvision(this.imagePathText),
        historicalContext = this.historicalContext,
        technique = this.technique,
        support = this.support,
        dimensions = this.dimensions,
        author = this.author.toUiModel(),
        culturalEra = this.culturalEra,
        artisticTrend = this.artisticTrend.toUiModel(),
        location = this.location.toUiModel(),
        hasBeenMarkedAsFavorite = this.hasBeenMarkedAsFavorite
    )