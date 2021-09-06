package cdglacier.galleryfordropbox.data.medium

import cdglacier.galleryfordropbox.model.Medium

interface MediumRepository {
    suspend fun listMedia(): Result<List<Medium>>
}