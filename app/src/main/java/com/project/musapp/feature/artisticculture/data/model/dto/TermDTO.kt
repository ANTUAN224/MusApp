package com.project.musapp.feature.artisticculture.data.model.dto

import com.project.musapp.feature.artisticculture.domain.model.TermDomainModel

data class TermDTO(
    val name: String,
    val definition: String
)

fun TermDTO.toDomainModel() =
    TermDomainModel(
        name = this.name,
        definition = this.definition
    )