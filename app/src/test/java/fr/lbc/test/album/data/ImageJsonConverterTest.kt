package fr.lbc.test.album.data

import fr.lbc.test.album.domain.Image
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ImageJsonConverterTest {

    private val converter = ImageJsonConverter()

    @Test
    fun buildImage() {
        // Given
        val imageJson = ImageJson(
            id = 1,
            albumId = 1,
            title = "image1",
            url = "url1",
            thumbnailUrl = "thumbnail1"
        )

        // When
        val result = converter.buildImage(imageJson)

        // Then
        assertThat(result).isEqualTo(
            Image(
                id = 1,
                albumId = 1,
                title = "image1",
                url = "url1",
                thumbnailUrl = "thumbnail1"
            )
        )
    }
}
