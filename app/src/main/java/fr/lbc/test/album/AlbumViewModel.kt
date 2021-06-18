package fr.lbc.test.album

import androidx.lifecycle.ViewModel
import fr.lbc.test.utils.SingleLiveEvent

class AlbumViewModel : ViewModel() {

    val loadMessage: SingleLiveEvent<String> = SingleLiveEvent()

    fun showAlbums() {
        loadMessage.postValue("LOADED")
    }
}
