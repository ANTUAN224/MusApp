package com.project.musapp.feature.artwork.domain.model.artwork

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
    val author: AuthorDomainModel,
    val culturalEra: String,
    val artisticTrend: ArtisticTrendDomainModel,
    val location: LocationDomainModel,
    val hasBeenMarkedAsFavorite: Boolean
)

fun ArtworkDomainModel.toUiModel() = ArtworkUiModel(
    id = this.id,
    title = this.title,
    description = this.description,
    imagePathText = this.imagePathText,
    historicalContext = this.historicalContext,
    technique = this.technique,
    support = this.support,
    author = this.author.toUiModel(),
    culturalEra = this.culturalEra,
    artisticTrend = this.artisticTrend.toUiModel(),
    location = this.location.toUiModel(),
    hasBeenMarkedAsFavorite = this.hasBeenMarkedAsFavorite
)