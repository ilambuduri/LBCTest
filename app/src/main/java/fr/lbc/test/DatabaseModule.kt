package fr.lbc.test

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideFormDatabase(context: Context): LBCDatabase {
        return Room.databaseBuilder(context, LBCDatabase::class.java, LBCDatabase.FILE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}
