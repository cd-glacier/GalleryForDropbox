package cdglacier.galleryfordropbox.data.medium

import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import cdglacier.galleryfordropbox.model.Medium
import cdglacier.galleryfordropbox.model.Photo
import com.dropbox.core.v2.DbxClientV2
import com.dropbox.core.v2.files.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayInputStream
import java.util.*

class MediumRepositoryImpl(
    var dropbox: DbxClientV2?
) : MediumRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun listMedia(): Result<List<Medium>> = withContext(Dispatchers.IO) {
        if (dropbox == null) {
            return@withContext Result.success(emptyList())
        }

        val thumbnailArgs = dropbox!!.files().listFolder("").entries
            .map {
                it.pathDisplay
            }
            .map {
                ThumbnailArg(it, ThumbnailFormat.PNG, ThumbnailSize.W640H480, ThumbnailMode.STRICT)
            }

        val bitMaps = dropbox!!.files().getThumbnailBatch(thumbnailArgs)
            .entries
            .map {
                val decoded = Base64.getDecoder().decode(it.successValue.thumbnail)
                BitmapFactory.decodeStream(ByteArrayInputStream(decoded))
            }

        val media = bitMaps.map { Photo("", it) }

        Result.success(media)
    }
}