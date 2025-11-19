package com.project.musapp.feature.collection.presentation.model

import com.project.musapp.feature.collection.domain.model.CollectionBatchDeletionDomainModel

data class CollectionBatchDeletionUiModel(
    val collectionIds: List<Long>
)

fun CollectionBatchDeletionUiModel.toDomainModel() =
    CollectionBatchDeletionDomainModel(
        collectionIds = this.collectionIds
    )