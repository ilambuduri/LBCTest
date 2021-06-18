package fr.lbc.test

import android.app.Application
import android.content.Context

class LBCApplication : Application() {

    companion object {
        fun getComponent(context: Context): MainComponent {
            return (context.applicationContext as LBCApplication).component
        }
    }

    private lateinit var component: MainComponent

    override fun onCreate() {
        super.onCreate()
        inject()
    }

    private fun inject() {
        component = DaggerMainComponent
            .builder()
            .mainModule(MainModule(this))
            .build()
        component.inject(this)
    }

}
