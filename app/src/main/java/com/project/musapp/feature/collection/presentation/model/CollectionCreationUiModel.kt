package com.project.musapp.feature.collection.presentation.model

import com.project.musapp.feature.collection.domain.model.CollectionCreationDomainModel

data class CollectionCreationUiModel(
    val title: String
)

fun CollectionCreationUiModel.toDomainModel() =
    CollectionCreationDomainModel(
        title = this.title
    )