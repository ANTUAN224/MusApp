package com.project.musapp.feature.collection.domain.model

import com.project.musapp.feature.collection.presentation.model.CollectionReadingUiModel

data class CollectionReadingDomainModel(
    val id: Long,
    val title: String
)

fun CollectionReadingDomainModel.toUiModel() =
    CollectionReadingUiModel(
        id = this.id,
        title = this.title
    )