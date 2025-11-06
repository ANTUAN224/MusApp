package com.project.musapp.core.artworkimageurlgetting.domain.usecase

import android.net.Uri
import com.project.musapp.core.artworkimageurlgetting.domain.repository.ArtworkImageUrlGettingRepository
import javax.inject.Inject

class GetArtworkImageUrlUseCase @Inject constructor(private val repository: ArtworkImageUrlGettingRepository) {
    suspend operator fun invoke(artworkImagePath: String): Result<Uri> {
        return runCatching { repository.getArtworkImageUrl(artworkImagePath) }
    }
}