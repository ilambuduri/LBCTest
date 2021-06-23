package fr.lbc.test.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import fr.lbc.test.album.domain.Image
import fr.lbc.test.detail.DetailViewModel.ImageResult
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setup() {
        viewModel = DetailViewModel()
    }

    @Test
    fun `presentError should call loadingResult with LoadingError value`() {
        // When
        viewModel.presentError()

        // Then
        assertThat(viewModel.loadingResult.value).isEqualTo(ImageResult.LoadingError)
    }

    @Test
    fun `presentImages should post value with formatted images`() {
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

        // When
        viewModel.presentImages(listOf(image1, image2))

        // Then
        assertThat(viewModel.loadingResult.value).isEqualTo(
            ImageResult.LoadedImages(
                listOf(
                    DetailViewModel.ImageUI(
                        id = 1,
                        title = "image1",
                        url = "url1"
                    ),
                    DetailViewModel.ImageUI(
                        id = 2,
                        title = "image2",
                        url = "url2"
                    )
                )
            )
        )
    }

}
