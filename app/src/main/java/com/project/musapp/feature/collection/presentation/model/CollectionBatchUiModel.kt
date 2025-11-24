package com.project.musapp.feature.collection.presentation.model

import com.project.musapp.feature.collection.domain.model.CollectionBatchDomainModel

data class CollectionBatchUiModel(
    val collectionIds: List<Long>
)

fun CollectionBatchUiModel.toDomainModel() =
    CollectionBatchDomainModel(
        collectionIds = this.collectionIds
    )