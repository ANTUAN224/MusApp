package com.project.musapp.feature.artisticculture.data.source.remote.api

import javax.inject.Inject

class ArtisticCultureTechnicalGlossaryGettingRetrofit @Inject constructor(
    private val artisticCultureApiService: ArtisticCultureApiService
) {
    suspend fun getAllTerms(
        userToken: String
    ) =
        artisticCultureApiService.getAllTerms(
            headerCompanionValue = "Bearer $userToken"
        ).body()!!
}