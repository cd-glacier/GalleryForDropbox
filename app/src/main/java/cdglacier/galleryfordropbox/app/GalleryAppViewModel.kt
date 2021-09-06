package cdglacier.galleryfordropbox.app

import androidx.lifecycle.*
import cdglacier.galleryfordropbox.data.medium.MediumRepository
import cdglacier.galleryfordropbox.model.Medium
import kotlinx.coroutines.launch

class GalleryAppViewModel(
    private val mediumRepository: MediumRepository
) : ViewModel() {
    private val _media = MutableLiveData<List<Medium>?>(null)
    val media: LiveData<List<Medium>?>
        get() = _media

    fun initializeMedia() = viewModelScope.launch {
        _media.value = mediumRepository.listMedia().getOrThrow()
    }

    class Factory(
        private val mediumRepository: MediumRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return GalleryAppViewModel(
                mediumRepository = mediumRepository
            ) as T
        }
    }
}