package fr.lbc.test.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fr.lbc.test.album.domain.Image

class DetailViewModel : ViewModel() {

    val loadingResult: MutableLiveData<ImageResult> = MutableLiveData()

    fun presentImages(imageList: List<Image>) {
        loadingResult.postValue(
            ImageResult.LoadedImages(
                imageList.map { image ->
                    ImageUI(
                        id = image.id,
                        title = image.title,
                        url = image.url
                    )
                }
            )
        )
    }

    fun presentError() {
        loadingResult.postValue(ImageResult.LoadingError)
    }

    sealed class ImageResult {
        object LoadingError : ImageResult()
        data class LoadedImages(
            val imageList: List<ImageUI>
        ) : ImageResult()
    }

    data class ImageUI(
        val id: Int,
        val url: String,
        val title: String
    )
}
