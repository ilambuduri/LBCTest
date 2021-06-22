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
        when (val loadResult = repository.loadAlbumList()) {
            ImageLoadingError -> viewModel.presentError()
            is ImagesLoaded -> {
                val groupedImageList = loadResult.imageList
                    .groupBy(Image::albumId)
                    .mapNotNull {
                        it.key to it.value.map(Image::thumbnailUrl)
                    }
                viewModel.presentAlbums(groupedImageList)
            }
        }
    }
}
