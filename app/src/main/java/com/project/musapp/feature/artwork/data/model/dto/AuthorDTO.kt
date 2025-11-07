package com.project.musapp.feature.artwork.data.model.dto

import com.project.musapp.feature.artwork.domain.model.author.AuthorDomainModel
import com.project.musapp.feature.artwork.domain.model.author.Sex

data class AuthorDTO(
    val historicallyKnownName: String,
    val sex: com.project.musapp.feature.artwork.domain.model.author.Sex,
    val livedYearRange: String
)

fun com.project.musapp.feature.artwork.data.model.dto.AuthorDTO.toDomainModel() =
    _root_ide_package_.com.project.musapp.feature.artwork.domain.model.author.AuthorDomainModel(
        historicallyKnownName = this.historicallyKnownName,
        sex = this.sex,
        livedYearRange = this.livedYearRange
    )