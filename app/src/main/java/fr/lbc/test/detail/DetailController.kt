package fr.lbc.test.detail

import fr.lbc.test.detail.domain.DetailInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DetailController(
    private val interactor: DetailInteractor,
    private val coroutineScope: CoroutineScope
) {

    fun loadAlbumDetail(albumId: Int) {
        coroutineScope.launch {
            interactor.loadAlbumDetail(albumId)
        }
    }
}
