package fr.lbc.test.album.data

import fr.lbc.test.album.domain.Image

class ImageLocalDataSource(
    private val converter: ImageDbConverter,
    private val imageDao: ImageDao
) {

    fun loadAlbumList(): List<Image> {
        return imageDao.getAlbumWithPreviewImages().map { imageDb ->
            converter.buildImage(imageDb)
        }
    }

    fun saveImageList(remoteImageList: List<Image>) {
        val imageDbList = remoteImageList.map { image ->
            converter.buildImageDb(image)
        }
        imageDao.saveImageList(imageDbList)
    }

    fun loadAlbumImages(albumId: Int): List<Image> {
        return imageDao.getAlbumImages(albumId).map { imageDb ->
            converter.buildImage(imageDb)
        }
    }
}
