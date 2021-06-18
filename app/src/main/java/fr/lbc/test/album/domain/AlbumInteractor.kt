package fr.lbc.test.album.domain

import fr.lbc.test.album.AlbumViewModel

class AlbumInteractor(
    private val viewModel: AlbumViewModel
) {

    fun loadAlbums() {
        viewModel.showAlbums()
    }
}
