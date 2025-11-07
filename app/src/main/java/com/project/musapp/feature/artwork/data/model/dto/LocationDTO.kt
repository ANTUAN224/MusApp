package com.project.musapp.feature.artwork.data.model.dto

import com.project.musapp.feature.artwork.domain.model.LocationDomainModel

data class LocationDTO(
    val name: String,
    val city: String,
    val country: String
)

fun com.project.musapp.feature.artwork.data.model.dto.LocationDTO.toDomainModel() =
    _root_ide_package_.com.project.musapp.feature.artwork.domain.model.LocationDomainModel(
        name = this.name,
        city = this.city,
        country = this.country
    )