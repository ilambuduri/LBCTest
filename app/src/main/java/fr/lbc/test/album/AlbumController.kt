package fr.lbc.test.album

import fr.lbc.test.album.domain.AlbumInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class AlbumController(
    private val interactor: AlbumInteractor,
    private val coroutineScope: CoroutineScope
) {
    fun loadAlbums() {
        coroutineScope.launch {
            interactor.loadAlbums()
        }
    }
}
