package com.project.musapp.feature.home.data.source.remote

import android.util.Log
import com.project.musapp.feature.home.data.model.dto.ArtworkPreviewDTO
import com.project.musapp.feature.home.data.source.remote.apiservice.HomeApiService
import javax.inject.Inject

class UserFavoriteArtworksGettingRetrofit @Inject constructor(
    private val homeApiService: HomeApiService
) {
    suspend fun getUserFavoriteArtworks(userToken: String): List<ArtworkPreviewDTO> {
        val response = homeApiService.getUserFavoriteArtworks(headerCompanionValue = "Bearer $userToken")

        Log.d("EJECUCIÓN", "Cuerpo de la respuesta -> ${response.body()}")
        return response.body()!!
    }
}