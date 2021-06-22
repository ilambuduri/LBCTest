package fr.lbc.test.album.domain

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.then
import fr.lbc.test.album.AlbumViewModel
import fr.lbc.test.album.data.AlbumRepository
import fr.lbc.test.album.data.ImageLoadingResult
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumInteractorTest {

    @Mock private lateinit var repository: AlbumRepository
    @Mock private lateinit var viewModel: AlbumViewModel
    @InjectMocks private lateinit var interactor: AlbumInteractor

    @Test
    fun `loadAlbums will call showError`() {
        // Given
        given(repository.loadAlbumList()).willReturn(ImageLoadingResult.ImageLoadingError)

        // When
        interactor.loadAlbums()

        // Then
        then(viewModel).should(only()).presentError()
    }

    @Test
    fun `loadAlbums will call showAlbums with a formatted list`() {
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
            albumId = 1,
            title = "image2",
            url = "url2",
            thumbnailUrl = "thumbnail2"
        )
        val image3 = Image(
            id = 3,
            albumId = 2,
            title = "image3",
            url = "url3",
            thumbnailUrl = "thumbnail3"
        )
        given(repository.loadAlbumList()).willReturn(
            ImageLoadingResult.ImagesLoaded(
                listOf(
                    image1,
                    image2,
                    image3
                )
            )
        )

        // When
        interactor.loadAlbums()

        // Then
        then(viewModel).should(only()).presentAlbums(
            listOf(
                1 to listOf("thumbnail1", "thumbnail2"),
                2 to listOf("thumbnail3")
            )
        )
    }
}
