package com.project.musapp.feature.artwork.presentation.model

import com.project.musapp.feature.artwork.domain.model.author.Sex

data class AuthorUiModel(
    val historicallyKnownName: String,
    val sex: com.project.musapp.feature.artwork.domain.model.author.Sex,
    val livedYearRange: String
)