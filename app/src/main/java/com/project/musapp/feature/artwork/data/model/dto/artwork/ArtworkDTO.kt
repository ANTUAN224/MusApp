package com.project.musapp.feature.artwork.data.model.dto.artwork

import com.project.musapp.feature.artwork.data.model.dto.ArtisticTrendDTO
import com.project.musapp.feature.artwork.data.model.dto.AuthorDTO
import com.project.musapp.feature.artwork.data.model.dto.LocationDTO
import com.project.musapp.feature.artwork.data.model.dto.toDomainModel
import com.project.musapp.feature.artwork.domain.model.artwork.ArtworkDomainModel

data class ArtworkDTO(
    val id: Long,
    val title: String,
    val description: String,
    val imagePathText: String,
    val historicalContext: String,
    val technique: String,
    val support: String,
    val author: AuthorDTO,
    val culturalEra: String,
    val artisticTrend: ArtisticTrendDTO,
    val location: LocationDTO,
    val hasBeenMarkedAsFavorite: Boolean
)

fun ArtworkDTO.toDomainModel() =
    ArtworkDomainModel(
        id = this.id,
        title = this.title,
        description = this.description,
        imagePathText = this.imagePathText,
        historicalContext = this.historicalContext,
        technique = this.technique,
        support = this.support,
        author = this.author.toDomainModel(),
        culturalEra = this.culturalEra,
        artisticTrend = this.artisticTrend.toDomainModel(),
        location = this.location.toDomainModel(),
        hasBeenMarkedAsFavorite = hasBeenMarkedAsFavorite
    )