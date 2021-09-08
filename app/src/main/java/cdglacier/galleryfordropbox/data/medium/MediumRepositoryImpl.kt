package cdglacier.galleryfordropbox.data.medium

import cdglacier.galleryfordropbox.model.Medium
import com.dropbox.core.v2.DbxClientV2
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MediumRepositoryImpl(
    var dropbox: DbxClientV2?
) : MediumRepository {
    override suspend fun listMedia(): Result<List<Medium>> = withContext(Dispatchers.IO) {
        val folders = dropbox?.files()?.listFolder("")
        println("---------")
        println(folders?.entries?.map { it.name })

        // TODO: get photo url

        Result.success(listOf())
    }
}