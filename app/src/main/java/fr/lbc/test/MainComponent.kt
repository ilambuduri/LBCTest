package fr.lbc.test

import dagger.Component
import fr.lbc.test.album.injection.AlbumComponent
import fr.lbc.test.album.injection.AlbumModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MainModule::class,
        NetworkModule::class,
        DatabaseModule::class
    ]
)
interface MainComponent {

    fun inject(application: LBCApplication)
    fun plus(albumModule: AlbumModule): AlbumComponent

}
