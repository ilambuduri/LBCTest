package fr.lbc.test.album.injection

import dagger.Subcomponent
import fr.lbc.test.album.AlbumFragment

@Subcomponent(
    modules = [
        AlbumModule::class
    ]
)
interface AlbumComponent {
    fun inject(fragment: AlbumFragment)
}
