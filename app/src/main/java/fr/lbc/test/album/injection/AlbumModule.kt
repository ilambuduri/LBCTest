package fr.lbc.test.album.injection

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.Module
import dagger.Provides
import fr.lbc.test.album.AlbumController
import fr.lbc.test.album.AlbumFragment
import fr.lbc.test.album.AlbumViewModel
import fr.lbc.test.album.data.ImageRepository
import fr.lbc.test.album.domain.AlbumInteractor
import fr.lbc.test.utils.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.plus

@Module
class AlbumModule(private val fragment: AlbumFragment) {

    @Provides
    fun provideController(
        interactor: AlbumInteractor
    ): AlbumController {
        return AlbumController(
            interactor = interactor,
            coroutineScope = fragment.lifecycleScope + Dispatchers.IO
        )
    }

    @Provides
    fun provideInteractor(
        viewModel: AlbumViewModel,
        repository: ImageRepository
    ): AlbumInteractor {
        return AlbumInteractor(repository, viewModel)
    }

    @Provides
    fun provideViewModel(): AlbumViewModel {
        val factory = ViewModelFactory {
            AlbumViewModel()
        }
        return ViewModelProvider(fragment, factory)[AlbumViewModel::class.java]
    }
}
