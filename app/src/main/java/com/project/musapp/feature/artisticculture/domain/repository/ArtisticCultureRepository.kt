package com.project.musapp.feature.artisticculture.domain.repository

import com.project.musapp.feature.artisticculture.domain.model.CuriosityDomainModel
import com.project.musapp.feature.artisticculture.domain.model.TermDomainModel

interface ArtisticCultureRepository {
    suspend fun getAllTerms(userToken: String): List<TermDomainModel>

    suspend fun getAllCuriosities(userToken: String): List<CuriosityDomainModel>
}