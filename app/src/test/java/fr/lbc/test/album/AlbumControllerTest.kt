package fr.lbc.test.album

import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.then
import fr.lbc.test.album.domain.AlbumInteractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AlbumControllerTest {

    @Mock private lateinit var interactor: AlbumInteractor
    private lateinit var controller: AlbumController

    @Before
    fun setup() {
        controller = AlbumController(
            interactor = interactor,
            coroutineScope = CoroutineScope(Dispatchers.Unconfined)
        )
    }

    @Test
    fun loadAlbums() {
        // When
        controller.loadAlbums()

        // Then
        then(interactor).should(only()).loadAlbums()
    }
}
