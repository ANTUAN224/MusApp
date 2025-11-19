package com.project.musapp.feature.collection.domain.model

import com.project.musapp.feature.collection.data.model.dto.CollectionBatchDeletionDTO

data class CollectionBatchDeletionDomainModel(
    val collectionIds: List<Long>
)

fun CollectionBatchDeletionDomainModel.toDTO() =
    CollectionBatchDeletionDTO(
        collectionIds = this.collectionIds
    )