package fr.lbc.test.album.data

import fr.lbc.test.album.domain.Image
import java.io.IOException

class ImageRemoteDataSource(
    private val service: ImageService,
    private val converter: ImageJsonConverter
) {

    fun loadImageList(): List<Image>? {
        return try {
            service.getImages().execute().body()?.map { imageJson ->
                converter.buildImage(imageJson)
            }
        } catch (ex: IOException) {
            null
        }
    }
}
