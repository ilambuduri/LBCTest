package fr.lbc.test.album.data

import fr.lbc.test.album.domain.Image

class ImageJsonConverter {
    fun buildImage(imageJson: ImageJson): Image {
        return Image(
            id = imageJson.id,
            albumId = imageJson.albumId,
            title = imageJson.title,
            url = imageJson.url,
            thumbnailUrl = imageJson.thumbnailUrl
        )
    }
}
