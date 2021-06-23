package fr.lbc.test.detail.injection

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.Module
import dagger.Provides
import fr.lbc.test.album.data.ImageRepository
import fr.lbc.test.detail.DetailController
import fr.lbc.test.detail.DetailFragment
import fr.lbc.test.detail.DetailViewModel
import fr.lbc.test.detail.domain.DetailInteractor
import fr.lbc.test.utils.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.plus

@Module
class DetailModule(private val fragment: DetailFragment) {

    @Provides
    fun provideController(
        interactor: DetailInteractor
    ): DetailController {
        return DetailController(
            interactor = interactor,
            coroutineScope = fragment.lifecycleScope + Dispatchers.IO
        )
    }

    @Provides
    fun provideInteractor(
        viewModel: DetailViewModel,
        repository: ImageRepository
    ): DetailInteractor {
        return DetailInteractor(repository, viewModel)
    }

    @Provides
    fun provideViewModel(): DetailViewModel {
        val factory = ViewModelFactory {
            DetailViewModel()
        }
        return ViewModelProvider(fragment, factory)[DetailViewModel::class.java]
    }
}
