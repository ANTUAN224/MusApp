package com.project.musapp.feature.collection.presentation.model

import com.project.musapp.feature.collection.domain.model.CollectionRenamingDomainModel

data class CollectionRenamingUiModel(
    val modifiedTitle: String
)

fun CollectionRenamingUiModel.toDomainModel() =
    CollectionRenamingDomainModel(
        modifiedTitle = this.modifiedTitle
    )