package fr.lbc.test.album.injection

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.Module
import dagger.Provides
import fr.lbc.test.LBCDatabase
import fr.lbc.test.album.AlbumController
import fr.lbc.test.album.AlbumFragment
import fr.lbc.test.album.AlbumViewModel
import fr.lbc.test.album.data.AlbumRepository
import fr.lbc.test.album.data.ImageDao
import fr.lbc.test.album.data.ImageDbConverter
import fr.lbc.test.album.data.ImageJsonConverter
import fr.lbc.test.album.data.ImageLocalDataSource
import fr.lbc.test.album.data.ImageRemoteDataSource
import fr.lbc.test.album.data.ImageService
import fr.lbc.test.album.domain.AlbumInteractor
import fr.lbc.test.utils.ViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.plus
import retrofit2.Retrofit

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
        repository: AlbumRepository
    ): AlbumInteractor {
        return AlbumInteractor(repository, viewModel)
    }

    @Provides
    fun provideRepository(
        localDataSource: ImageLocalDataSource,
        remoteDataSource: ImageRemoteDataSource
    ): AlbumRepository {
        return AlbumRepository(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource
        )
    }

    @Provides
    fun provideLocalDataSource(
        converter: ImageDbConverter,
        dao: ImageDao
    ): ImageLocalDataSource {
        return ImageLocalDataSource(
            converter = converter,
            imageDao = dao
        )
    }

    @Provides
    fun provideImageDbConverter(): ImageDbConverter = ImageDbConverter()

    @Provides
    fun provideImageJsonConverter(): ImageJsonConverter = ImageJsonConverter()

    @Provides
    fun provideRemoteDataSource(
        converter: ImageJsonConverter,
        service: ImageService
    ): ImageRemoteDataSource {
        return ImageRemoteDataSource(
            converter = converter,
            service = service
        )
    }

    @Provides
    fun provideImageDao(database: LBCDatabase): ImageDao {
        return database.imageDao()
    }

    @Provides
    fun provideService(retrofit: Retrofit): ImageService {
        return retrofit.create(ImageService::class.java)
    }

    @Provides
    fun provideViewModel(): AlbumViewModel {
        val factory = ViewModelFactory {
            AlbumViewModel()
        }
        return ViewModelProvider(fragment, factory)[AlbumViewModel::class.java]
    }
}
