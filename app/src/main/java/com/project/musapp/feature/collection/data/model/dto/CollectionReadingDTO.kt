package com.project.musapp.feature.collection.data.model.dto

import com.project.musapp.feature.collection.domain.model.CollectionReadingDomainModel

data class CollectionReadingDTO(
    val id: Long,
    val title: String
)

fun CollectionReadingDTO.toDomainModel() =
    CollectionReadingDomainModel(
        id = this.id,
        title = this.title
    )