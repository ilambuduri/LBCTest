package fr.lbc.test.album.data

import fr.lbc.test.album.domain.Image

class ImageDbConverter {

    fun buildImage(image: ImageDb): Image {
        return Image(
            id = image.id,
            albumId = image.albumId,
            title = image.title,
            url = image.url,
            thumbnailUrl = image.thumbnailUrl
        )
    }

    fun buildImageDb(image: Image): ImageDb {
        return ImageDb(
            id = image.id,
            albumId = image.albumId,
            title = image.title,
            url = image.url,
            thumbnailUrl = image.thumbnailUrl
        )
    }
}
