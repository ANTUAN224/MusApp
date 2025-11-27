package com.project.musapp.feature.artisticculture.data.source.remote.api

import javax.inject.Inject

class ArtisticCultureCuriosityGettingRetrofit @Inject constructor(
    private val artisticCultureApiService: ArtisticCultureApiService
) {
    suspend fun getAllCuriosities(
        userToken: String
    ) =
        artisticCultureApiService.getAllCuriosities(
            headerCompanionValue = "Bearer $userToken"
        ).body()!!
}