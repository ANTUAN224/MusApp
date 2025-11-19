package com.project.musapp.feature.collection.domain.model

import com.project.musapp.feature.collection.data.model.dto.CollectionCreationDTO

data class CollectionCreationDomainModel(
    val title: String
)

fun CollectionCreationDomainModel.toDTO() =
    CollectionCreationDTO(
        title = this.title
    )