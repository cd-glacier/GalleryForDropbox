package cdglacier.galleryfordropbox.data.medium

import cdglacier.galleryfordropbox.model.Medium
import cdglacier.galleryfordropbox.model.fakeMedia
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FakeMediumRepositoryImpl : MediumRepository {
    override suspend fun listMedia(): Result<List<Medium>> = withContext(Dispatchers.IO) {
        Result.success(fakeMedia())
    }
}