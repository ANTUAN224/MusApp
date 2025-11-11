package com.project.musapp.feature.artwork.data.source.remote.api

import com.project.musapp.core.network.domain.exception.NetworkException
import com.project.musapp.feature.artwork.data.model.dto.artwork.ArtworkDTO
import java.io.IOException
import javax.inject.Inject

class ArtworkGettingRetrofit @Inject constructor(
    private val artworkApiService: ArtworkApiService
) {
    suspend fun getArtwork(userToken: String, artworkId: Long): ArtworkDTO {
        try {
            return artworkApiService.getArtwork(
                headerCompanionValue = "Bearer $userToken",
                artworkId = artworkId
            ).body()!!
        } catch (_: IOException) {
            throw NetworkException.NoInternetConnectionException
        }
    }
}