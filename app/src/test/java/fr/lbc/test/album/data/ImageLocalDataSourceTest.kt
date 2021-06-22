package fr.lbc.test.album.data

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.then
import fr.lbc.test.album.domain.Image
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ImageLocalDataSourceTest {

    @Mock private lateinit var dao: ImageDao
    @Mock private lateinit var converter: ImageDbConverter
    @InjectMocks private lateinit var dataSource: ImageLocalDataSource

    @Test
    fun `loadImageList will return emptyList`() {
        // Given
        given(dao.getAlbumWithPreviewImages()).willReturn(emptyList())

        // When
        val result = dataSource.loadAlbumList()

        // Then
        assertThat(result.size).isEqualTo(0)
    }

    @Test
    fun `loadImageList will return something`() {
        // Given
        val image1 = ImageDb(
            id = 1,
            albumId = 1,
            title = "image1",
            url = "url1",
            thumbnailUrl = "thumbnail1"
        )
        val image2 = ImageDb(
            id = 2,
            albumId = 2,
            title = "image2",
            url = "url2",
            thumbnailUrl = "thumbnail2"
        )
        val convertedImage1 = Image(
            id = 1,
            albumId = 1,
            title = "image1",
            url = "url1",
            thumbnailUrl = "thumbnail1"
        )
        val convertedImage2 = Image(
            id = 2,
            albumId = 2,
            title = "image2",
            url = "url2",
            thumbnailUrl = "thumbnail2"
        )
        given(dao.getAlbumWithPreviewImages()).willReturn(listOf(image1, image2))
        given(converter.buildImage(image1)).willReturn(convertedImage1)
        given(converter.buildImage(image2)).willReturn(convertedImage2)

        // When
        val result = dataSource.loadAlbumList()

        // Then
        assertThat(result).isEqualTo(listOf(convertedImage1, convertedImage2))
    }

    @Test
    fun `saveImageList will call dao with converted images`() {
        // Given
        val image1 = Image(
            id = 1,
            albumId = 1,
            title = "image1",
            url = "url1",
            thumbnailUrl = "thumbnail1"
        )
        val image2 = Image(
            id = 2,
            albumId = 2,
            title = "image2",
            url = "url2",
            thumbnailUrl = "thumbnail2"
        )
        val convertedImage1 = ImageDb(
            id = 1,
            albumId = 1,
            title = "image1",
            url = "url1",
            thumbnailUrl = "thumbnail1"
        )
        val convertedImage2 = ImageDb(
            id = 2,
            albumId = 2,
            title = "image2",
            url = "url2",
            thumbnailUrl = "thumbnail2"
        )
        given(converter.buildImageDb(image1)).willReturn(convertedImage1)
        given(converter.buildImageDb(image2)).willReturn(convertedImage2)

        // When
        dataSource.saveImageList(listOf(image1, image2))

        // Then
        then(dao).should(only()).saveImageList(listOf(convertedImage1, convertedImage2))
    }
}
