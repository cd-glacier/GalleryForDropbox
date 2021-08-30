package cdglacier.galleryfordropbox.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cdglacier.galleryfordropbox.model.Medium
import cdglacier.galleryfordropbox.model.fakeMedia

class GalleryAppViewModel : ViewModel() {
    private val _media = MutableLiveData<List<Medium>?>(null)
    val media: LiveData<List<Medium>?>
        get() = _media

    fun initializeMedia() {
        _media.value = fakeMedia()
    }

    class Factory : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return GalleryAppViewModel() as T
        }
    }
}