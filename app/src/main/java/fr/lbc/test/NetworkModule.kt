package fr.lbc.test

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        const val TIMEOUT = 60L
        const val CACHE_SIZE = 25L * 1024 * 1024 // 25 MiB
    }

    @Singleton
    @Provides
    fun provideOkhttpClient(
        context: Context
    ): OkHttpClient.Builder {
        return OkHttpClient()
            .newBuilder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .cache(Cache(context.cacheDir, CACHE_SIZE))
    }

    @Provides
    fun provideRetrofit(
        client: OkHttpClient.Builder
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://static.leboncoin.fr/")
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
