package fr.lbc.test.detail.domain

import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.then
import fr.lbc.test.album.data.ImageLoadingResult
import fr.lbc.test.album.data.ImageRepository
import fr.lbc.test.album.domain.Image
import fr.lbc.test.detail.DetailViewModel
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailInteractorTest {

    @Mock private lateinit var repository: ImageRepository
    @Mock private lateinit var viewModel: DetailViewModel
    @InjectMocks private lateinit var interactor: DetailInteractor

    @Test
    fun `loadAlbumDetail will call presentError`() {
        // Given
        given(repository.loadAlbumImages(1)).willReturn(ImageLoadingResult.ImageLoadingError)

        // When
        interactor.loadAlbumDetail(1)

        // Then
        then(viewModel).should(only()).presentError()
    }

    @Test
    fun `loadAlbumDetail will call presentImages`() {
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
        given(repository.loadAlbumImages(1)).willReturn(
            ImageLoadingResult.ImagesLoaded(listOf(image1, image2))
        )

        // When
        interactor.loadAlbumDetail(1)

        // Then
        then(viewModel).should(only()).presentImages(listOf(image1, image2))
    }
}
