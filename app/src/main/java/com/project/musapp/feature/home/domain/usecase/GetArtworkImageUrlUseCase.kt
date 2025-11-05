package com.project.musapp.feature.home.domain.usecase

import android.net.Uri
import com.project.musapp.feature.home.domain.repository.HomeRepository
import javax.inject.Inject

class GetArtworkImageUrlUseCase  @Inject constructor(private val repository: HomeRepository) {
    suspend operator fun invoke(artworkImagePath: String) : Result<Uri> {
        return runCatching { repository.getArtworkImageUrl(artworkImagePath) }
    }
}