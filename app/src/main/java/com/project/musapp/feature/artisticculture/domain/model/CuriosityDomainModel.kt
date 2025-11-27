package com.project.musapp.feature.artisticculture.domain.model

import com.project.musapp.feature.artisticculture.presentation.model.CuriosityUiModel

data class CuriosityDomainModel(
    val title: String,
    val description: String
)

fun CuriosityDomainModel.toUiModel() =
    CuriosityUiModel(
        title = this.title,
        description = this.description
    )