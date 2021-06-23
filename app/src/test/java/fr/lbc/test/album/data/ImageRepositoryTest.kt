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
class ImageRepositoryTest {

    @Mock private lateinit var localDataSource: ImageLocalDataSource
    @Mock private lateinit var remoteDataSource: ImageRemoteDataSource
    @InjectMocks private lateinit var repository: ImageRepository

    @Test
    fun `loadAlbumList local datasource will return emptyList`() {
        // Given
        val image = Image(
            id = 1,
            albumId = 1,
            title = "image1",
            url = "url1",
            thumbnailUrl = "thumbnail1"
        )
        given(localDataSource.loadAlbumList()).willReturn(emptyList())
        given(remoteDataSource.loadImageList()).willReturn(listOf(image))

        // When
        val result = repository.loadAlbumList()

        // Then
        then(localDataSource).should().loadAlbumList()
        then(remoteDataSource).should(only()).loadImageList()
        then(localDataSource).should().saveImageList(listOf(image))
        assertThat(result).isEqualTo(ImageLoadingResult.ImagesLoaded(listOf(image)))
    }

    @Test
    fun `loadAlbumList local datasource will return emptyList but remote will crash`() {
        // Given
        given(localDataSource.loadAlbumList()).willReturn(emptyList())
        given(remoteDataSource.loadImageList()).willReturn(null)

        // When
        val result = repository.loadAlbumList()

        // Then
        then(localDataSource).should(only()).loadAlbumList()
        then(remoteDataSource).should(only()).loadImageList()
        assertThat(result).isEqualTo(ImageLoadingResult.ImageLoadingError)
    }

    @Test
    fun `loadAlbumList local datasource will return stuff`() {
        // Given
        val image = Image(
            id = 1,
            albumId = 1,
            title = "image1",
            url = "url1",
            thumbnailUrl = "thumbnail1"
        )
        given(localDataSource.loadAlbumList()).willReturn(listOf(image))

        // When
        val result = repository.loadAlbumList()

        // Then
        then(localDataSource).should(only()).loadAlbumList()
        then(remoteDataSource).shouldHaveZeroInteractions()
        assertThat(result).isEqualTo(ImageLoadingResult.ImagesLoaded(listOf(image)))
    }
}
