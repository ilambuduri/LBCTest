package fr.lbc.test.album.data

import fr.lbc.test.album.domain.Image

class ImageRepository(
    private val localDataSource: ImageLocalDataSource,
    private val remoteDataSource: ImageRemoteDataSource
) {
    fun loadAlbumList(): ImageLoadingResult {

        val localImageList = localDataSource.loadAlbumList()
        return if (localImageList.isNotEmpty()) {
            ImageLoadingResult.ImagesLoaded(localImageList)
        } else {
            val remoteImageList = remoteDataSource.loadImageList()
            remoteImageList?.let { imageList ->
                localDataSource.saveImageList(imageList)
                ImageLoadingResult.ImagesLoaded(imageList)
            } ?: ImageLoadingResult.ImageLoadingError
        }
    }

    fun loadAlbumImages(albumId: Int): ImageLoadingResult {
        val imageList = localDataSource.loadAlbumImages(albumId)
        return if (imageList.isNotEmpty()) {
            ImageLoadingResult.ImagesLoaded(imageList)
        } else {
            ImageLoadingResult.ImageLoadingError
        }
    }
}

sealed class ImageLoadingResult {

    data class ImagesLoaded(
        val imageList: List<Image>
    ) : ImageLoadingResult()

    object ImageLoadingError : ImageLoadingResult()
}
