package com.project.musapp.feature.collection.domain.model

import com.project.musapp.feature.collection.data.model.dto.CollectionRenamingDTO

data class CollectionRenamingDomainModel(
    val modifiedTitle: String
)

fun CollectionRenamingDomainModel.toDTO() =
    CollectionRenamingDTO(
        modifiedTitle = this.modifiedTitle
    )