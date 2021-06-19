package fr.lbc.test.album

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AlbumViewModel : ViewModel() {

    val loadingResult: MutableLiveData<AlbumResult> = MutableLiveData()

    fun showAlbums(groupedImageList: List<Pair<Int, List<String>>>) {
        loadingResult.postValue(AlbumResult.LoadedAlbums(groupedImageList))
    }

    fun showError() {
        loadingResult.postValue(AlbumResult.LoadingError)
    }

    sealed class AlbumResult {
        object LoadingError : AlbumResult()
        data class LoadedAlbums(
            val groupedImageList: List<Pair<Int, List<String>>>
        ) : AlbumResult()
    }
}
