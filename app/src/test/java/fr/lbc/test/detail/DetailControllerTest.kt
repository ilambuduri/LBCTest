package fr.lbc.test.detail

import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.then
import fr.lbc.test.detail.domain.DetailInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailControllerTest {
    @Mock private lateinit var interactor: DetailInteractor
    private lateinit var controller: DetailController

    @Before
    fun setup() {
        controller = DetailController(
            interactor = interactor,
            coroutineScope = CoroutineScope(Dispatchers.Unconfined)
        )
    }

    @Test
    fun loadAlbums() {
        // When
        controller.loadAlbumDetail(1)

        // Then
        then(interactor).should(only()).loadAlbumDetail(1)
    }
}
