package com.project.musapp.feature.artwork.domain.model.author

import com.project.musapp.feature.artwork.presentation.model.AuthorUiModel

data class AuthorDomainModel(
    val historicallyKnownName: String,
    val sex: com.project.musapp.feature.artwork.domain.model.author.Sex,
    val livedYearRange: String
)

fun com.project.musapp.feature.artwork.domain.model.author.AuthorDomainModel.toUiModel() =
    _root_ide_package_.com.project.musapp.feature.artwork.presentation.model.AuthorUiModel(
        historicallyKnownName = this.historicallyKnownName,
        sex = this.sex,
        livedYearRange = this.livedYearRange
    )