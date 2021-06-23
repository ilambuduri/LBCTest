package fr.lbc.test.album.injection

import dagger.Module
import dagger.Provides
import fr.lbc.test.LBCDatabase
import fr.lbc.test.album.data.ImageDao
import fr.lbc.test.album.data.ImageDbConverter
import fr.lbc.test.album.data.ImageJsonConverter
import fr.lbc.test.album.data.ImageLocalDataSource
import fr.lbc.test.album.data.ImageRemoteDataSource
import fr.lbc.test.album.data.ImageRepository
import fr.lbc.test.album.data.ImageService
import retrofit2.Retrofit

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(
        localDataSource: ImageLocalDataSource,
        remoteDataSource: ImageRemoteDataSource
    ): ImageRepository {
        return ImageRepository(
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
}
