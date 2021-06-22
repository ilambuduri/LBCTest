package fr.lbc.test.album.data

import fr.lbc.test.album.domain.Image
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ImageDbConverterTest {

    private val converter = ImageDbConverter()

    @Test
    fun buildImage() {
        // Given
        val imageJson = ImageDb(
            id = 1,
            albumId = 1,
            title = "image1",
            url = "url1",
            thumbnailUrl = "thumbnail1"
        )

        // When
        val result = converter.buildImage(imageJson)

        // Then
        Assertions.assertThat(result).isEqualTo(
            Image(
                id = 1,
                albumId = 1,
                title = "image1",
                url = "url1",
                thumbnailUrl = "thumbnail1"
            )
        )
    }

    @Test
    fun buildImageDb() {
        // Given
        val image = Image(
            id = 1,
            albumId = 1,
            title = "image1",
            url = "url1",
            thumbnailUrl = "thumbnail1"
        )

        // When
        val result = converter.buildImageDb(image)

        // Then
        Assertions.assertThat(result).isEqualTo(
            ImageDb(
                id = 1,
                albumId = 1,
                title = "image1",
                url = "url1",
                thumbnailUrl = "thumbnail1"
            )
        )
    }
}