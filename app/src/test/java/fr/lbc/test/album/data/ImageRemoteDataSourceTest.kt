package fr.lbc.test.album.data

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import fr.lbc.test.album.domain.Image
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Answers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

@RunWith(MockitoJUnitRunner::class)
class ImageRemoteDataSourceTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private lateinit var service: ImageService
    @Mock private lateinit var converter: ImageJsonConverter
    @InjectMocks private lateinit var dataSource: ImageRemoteDataSource

    @Test
    fun `loadImageList will return stuff`() {
        // Given
        val imageJson = ImageJson(
            id = 1,
            albumId = 1,
            title = "image1",
            url = "url1",
            thumbnailUrl = "thumbnail1"
        )
        val convertedImage = Image(
            id = 1,
            albumId = 1,
            title = "image",
            url = "url",
            thumbnailUrl = "thumbnail"
        )
        val call = mock<Call<List<ImageJson>>> {
            on { execute() } doReturn Response.success(listOf(imageJson))
        }
        given(service.getImages()).willReturn(call)
        given(converter.buildImage(imageJson)).willReturn(convertedImage)

        // When
        val result = dataSource.loadImageList()

        // Then
        assertThat(result).isEqualTo(listOf(convertedImage))
    }

    @Test
    fun `loadImageList will return null when technical exception`() {
        // Given
        val call = mock<Call<List<ImageJson>>> {
            on { execute() } doThrow IOException()
        }
        given(service.getImages()).willReturn(call)

        // When
        val result = dataSource.loadImageList()

        // Then
        assertThat(result).isEqualTo(null)
    }

    @Test
    fun `loadImageList will return null when no result from back`() {
        // Given
        val call = mock<Call<List<ImageJson>>> {
            on { execute() } doReturn Response.error(403, mock())
        }
        given(service.getImages()).willReturn(call)

        // When
        val result = dataSource.loadImageList()

        // Then
        assertThat(result).isEqualTo(null)
    }
}
