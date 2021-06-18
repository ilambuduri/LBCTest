package fr.lbc.test

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Module
class MainModule(
    private val application: Application
) {

    @Provides
    fun provideIOCoroutineScope(
        coroutineDispatcher: CoroutineDispatcher
    ): CoroutineScope = CoroutineScope(coroutineDispatcher)

    @Provides
    fun proviceCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideContext(): Context = application

    @Provides
    fun provideResources(): Resources = application.resources
}
