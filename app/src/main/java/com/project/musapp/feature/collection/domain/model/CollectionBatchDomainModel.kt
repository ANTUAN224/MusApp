package com.project.musapp.feature.collection.domain.model

import com.project.musapp.feature.collection.data.model.dto.CollectionBatchDTO

data class CollectionBatchDomainModel(
    val collectionIds: List<Long>
)

fun CollectionBatchDomainModel.toDTO() =
    CollectionBatchDTO(
        collectionIds = this.collectionIds
    )