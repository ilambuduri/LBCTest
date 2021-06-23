package fr.lbc.test

import dagger.Component
import fr.lbc.test.album.injection.AlbumComponent
import fr.lbc.test.album.injection.AlbumModule
import fr.lbc.test.album.injection.RepositoryModule
import fr.lbc.test.detail.injection.DetailComponent
import fr.lbc.test.detail.injection.DetailModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MainModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        RepositoryModule::class
    ]
)
interface MainComponent {

    fun inject(application: LBCApplication)
    fun plus(albumModule: AlbumModule): AlbumComponent
    fun plus(detailModule: DetailModule): DetailComponent
}
