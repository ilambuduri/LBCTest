package fr.lbc.test.detail.domain

import fr.lbc.test.album.data.ImageLoadingResult
import fr.lbc.test.album.data.ImageRepository
import fr.lbc.test.detail.DetailViewModel

class DetailInteractor(
    private val repository: ImageRepository,
    private val viewModel: DetailViewModel
) {

    fun loadAlbumDetail(albumId: Int) {
        val imageResult = repository.loadAlbumImages(albumId)
        when (imageResult) {
            ImageLoadingResult.ImageLoadingError -> viewModel.presentError()
            is ImageLoadingResult.ImagesLoaded -> viewModel.presentImages(imageResult.imageList)
        }

    }
}
