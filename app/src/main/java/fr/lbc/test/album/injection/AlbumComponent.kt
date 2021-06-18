package fr.lbc.test.album.injection

import dagger.Subcomponent
import fr.lbc.test.RepositoryModule
import fr.lbc.test.album.AlbumFragment

@Subcomponent(
    modules = [
        AlbumModule::class,
        RepositoryModule::class
    ]
)
interface AlbumComponent {
    fun inject(fragment: AlbumFragment)
}
