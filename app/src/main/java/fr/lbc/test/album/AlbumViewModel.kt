package fr.lbc.test.album

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AlbumViewModel : ViewModel() {

    val loadingResult: MutableLiveData<AlbumResult> = MutableLiveData()

    fun presentAlbums(groupedImageList: List<Pair<Int, List<String>>>) {
        loadingResult.postValue(AlbumResult.LoadedAlbums(
            groupedImageList.map { album ->
                AlbumUI(album.first, album.second)
            }
        ))
    }

    fun presentError() {
        loadingResult.postValue(AlbumResult.LoadingError)
    }

    sealed class AlbumResult {
        object LoadingError : AlbumResult()
        data class LoadedAlbums(
            val groupedImageList: List<AlbumUI>
        ) : AlbumResult()
    }

    data class AlbumUI(
        val albumId: Int,
        val previewImageList: List<String>
    )
}
