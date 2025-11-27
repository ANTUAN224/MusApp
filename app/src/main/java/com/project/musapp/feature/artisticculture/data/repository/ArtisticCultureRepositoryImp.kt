package com.project.musapp.feature.artisticculture.data.repository

import com.project.musapp.core.network.domain.exception.NetworkException
import com.project.musapp.feature.artisticculture.data.model.dto.toDomainModel
import com.project.musapp.feature.artisticculture.data.source.remote.api.ArtisticCultureCuriosityGettingRetrofit
import com.project.musapp.feature.artisticculture.data.source.remote.api.ArtisticCultureTechnicalGlossaryGettingRetrofit
import com.project.musapp.feature.artisticculture.domain.model.CuriosityDomainModel
import com.project.musapp.feature.artisticculture.domain.model.TermDomainModel
import com.project.musapp.feature.artisticculture.domain.repository.ArtisticCultureRepository
import okio.IOException
import javax.inject.Inject

class ArtisticCultureRepositoryImp @Inject constructor(
    private val artisticCultureTechnicalGlossaryGettingRetrofit:
    ArtisticCultureTechnicalGlossaryGettingRetrofit,
    private val artisticCultureCuriosityGettingRetrofit:
    ArtisticCultureCuriosityGettingRetrofit
) : ArtisticCultureRepository {
    override suspend fun getAllTerms(userToken: String): List<TermDomainModel> {
        try {
            return artisticCultureTechnicalGlossaryGettingRetrofit
                .getAllTerms(userToken = userToken)
                .map { termDTO -> termDTO.toDomainModel() }
        } catch (_: IOException) {
            throw NetworkException.NoInternetConnectionException
        }
    }

    override suspend fun getAllCuriosities(userToken: String): List<CuriosityDomainModel> {
        try {
            return artisticCultureCuriosityGettingRetrofit
                .getAllCuriosities(userToken = userToken)
                .map { curiosityDTO -> curiosityDTO.toDomainModel() }
        } catch (_: IOException) {
            throw NetworkException.NoInternetConnectionException

        }
    }
}