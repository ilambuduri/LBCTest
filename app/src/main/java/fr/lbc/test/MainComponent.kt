package fr.lbc.test

import dagger.Component
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

}
