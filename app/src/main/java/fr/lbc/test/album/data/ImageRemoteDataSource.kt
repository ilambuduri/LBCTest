package fr.lbc.test.album.data

import fr.lbc.test.album.domain.Image

class ImageRemoteDataSource(
    private val service: ImageService,
    private val converter: ImageJsonConverter
) {

    fun loadImageList(): List<Image>? {
        return service.getImages().execute().body()?.map { imageJson ->
            converter.buildImage(imageJson)
        }
    }
}
