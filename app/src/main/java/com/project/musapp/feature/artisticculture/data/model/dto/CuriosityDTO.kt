package com.project.musapp.feature.artisticculture.data.model.dto

import com.project.musapp.feature.artisticculture.domain.model.CuriosityDomainModel

data class CuriosityDTO(
    val title: String,
    val description: String
)

fun CuriosityDTO.toDomainModel() =
    CuriosityDomainModel(
        title = this.title,
        description = this.description
    )