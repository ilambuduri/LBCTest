package fr.lbc.test.album

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import fr.lbc.test.album.AlbumViewModel.AlbumResult
import fr.lbc.test.album.AlbumViewModel.AlbumUI
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: AlbumViewModel

    @Before
    fun setup() {
        viewModel = AlbumViewModel()
    }

    @Test
    fun `presentError should call loadingResult with LoadingError value`() {
        // When
        viewModel.presentError()

        // Then
        assertThat(viewModel.loadingResult.value).isEqualTo(AlbumResult.LoadingError)
    }

    @Test
    fun `presentAlbums should transform list of pairs to UI data class`() {
        // Given

        // When
        viewModel.presentAlbums(
            listOf(
                1 to listOf("thumbnail1", "thumbnail2"),
                2 to listOf("thumbnail3")
            )
        )

        // Then
        assertThat(viewModel.loadingResult.value).isEqualTo(
            AlbumResult.LoadedAlbums(
                listOf(
                    AlbumUI(
                        albumId = 1,
                        previewImageList = listOf("thumbnail1", "thumbnail2")
                    ),
                    AlbumUI(
                        albumId = 2,
                        previewImageList = listOf("thumbnail3")
                    )
                )
            )
        )
    }
}