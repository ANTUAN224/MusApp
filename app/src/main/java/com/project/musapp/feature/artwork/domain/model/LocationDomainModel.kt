package com.project.musapp.feature.artwork.domain.model

import com.project.musapp.feature.artwork.presentation.model.LocationUiModel

data class LocationDomainModel(
    val name: String,
    val city: String,
    val country: String
)

fun com.project.musapp.feature.artwork.domain.model.LocationDomainModel.toUiModel() =
    _root_ide_package_.com.project.musapp.feature.artwork.presentation.model.LocationUiModel(
        name = this.name,
        city = this.city,
        country = this.country
    )