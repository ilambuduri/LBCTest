package fr.lbc.test.album.domain

import fr.lbc.test.album.AlbumViewModel
import fr.lbc.test.album.data.AlbumRepository
import fr.lbc.test.album.data.ImageLoadingResult.ImageLoadingError
import fr.lbc.test.album.data.ImageLoadingResult.ImagesLoaded

class AlbumInteractor(
    private val repository: AlbumRepository,
    private val viewModel: AlbumViewModel
) {

    fun loadAlbums() {
        val loadResult = repository.loadAlbumList()

        when (loadResult) {
            ImageLoadingError -> viewModel.showError()
            is ImagesLoaded -> {
                val groupedImageList = loadResult.imageList
                    .groupBy(Image::albumId)
                    .map {
                        it.key to it.value.map(Image::thumbnailUrl)
                    }
                viewModel.showAlbums(groupedImageList)
            }
        }
    }
}
