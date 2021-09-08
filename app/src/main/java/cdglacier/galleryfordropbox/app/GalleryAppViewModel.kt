package cdglacier.galleryfordropbox.app

import android.app.Application
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.*
import cdglacier.galleryfordropbox.data.medium.MediumRepository
import cdglacier.galleryfordropbox.data.medium.MediumRepositoryImpl
import cdglacier.galleryfordropbox.model.Medium
import com.dropbox.core.oauth.DbxCredential
import com.dropbox.core.v2.DbxClientV2
import kotlinx.coroutines.launch

class GalleryAppViewModel(
    private val app: Application,
    private val mediumRepository: MediumRepository,
    var dropbox: DbxClientV2?
) : AndroidViewModel(app) {
    private val sharedPreferenceName = "gallery-box"
    private val sharedPreferenceKey = "credential"

    private val _media = MutableLiveData<List<Medium>?>(null)
    val media: LiveData<List<Medium>?>
        get() = _media

    fun setDropboxClient(client: DbxClientV2) {
        dropbox = client
    }

    fun initializeMedia() = viewModelScope.launch {
        (mediumRepository as MediumRepositoryImpl).dropbox = dropbox
        _media.value = mediumRepository.listMedia().getOrThrow()
    }

    fun getLocalCredential(): DbxCredential? {
        val sharedPreferences = app.getSharedPreferences(sharedPreferenceName, MODE_PRIVATE)
        val serializedCredential =
            sharedPreferences.getString(sharedPreferenceKey, null) ?: return null
        return DbxCredential.Reader.readFully(serializedCredential)
    }

    fun storeCredentialLocally(dbxCredential: DbxCredential) {
        val sharedPreferences = app.getSharedPreferences(sharedPreferenceName, MODE_PRIVATE)
        sharedPreferences.edit().putString(sharedPreferenceKey, dbxCredential.toString()).apply()
    }

    fun revokeDropbox() {
        dropbox?.auth()?.tokenRevoke()
        val sharedPreferences = app.getSharedPreferences(sharedPreferenceName, MODE_PRIVATE)
        sharedPreferences.edit().remove(sharedPreferenceKey).apply()
    }

    class Factory(
        private val app: Application,
        private val mediumRepository: MediumRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return GalleryAppViewModel(
                app = app,
                mediumRepository = mediumRepository,
                dropbox = null
            ) as T
        }
    }
}