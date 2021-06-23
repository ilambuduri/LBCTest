package fr.lbc.test.detail.injection

import dagger.Subcomponent
import fr.lbc.test.detail.DetailFragment

@Subcomponent(
    modules = [
        DetailModule::class
    ]
)
interface DetailComponent {
    fun inject(fragment: DetailFragment)
}
