package com.project.musapp.feature.artisticculture.domain.model

import com.project.musapp.feature.artisticculture.presentation.model.TermUiModel

data class TermDomainModel(
    val name: String,
    val definition: String
)

fun TermDomainModel.toUiModel() =
    TermUiModel(
        name = this.name,
        definition = this.definition
    )