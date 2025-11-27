package com.project.musapp.feature.artisticculture.data.source.remote.api

import com.project.musapp.feature.artisticculture.data.model.dto.CuriosityDTO
import com.project.musapp.feature.artisticculture.data.model.dto.TermDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ArtisticCultureApiService {
    @GET("technical-glossary")
    suspend fun getAllTerms(
        @Header("Authorization") headerCompanionValue: String
    ): Response<List<TermDTO>>

    @GET("curiosities")
    suspend fun getAllCuriosities(
        @Header("Authorization") headerCompanionValue: String
    ): Response<List<CuriosityDTO>>
}