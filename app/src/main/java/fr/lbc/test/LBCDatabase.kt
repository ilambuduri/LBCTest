package fr.lbc.test

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.lbc.test.album.data.ImageDao
import fr.lbc.test.album.data.ImageDb

@Database(
    entities = [
        ImageDb::class
    ],
    version = 2,
    exportSchema = false
)
abstract class LBCDatabase : RoomDatabase() {

    companion object {
        const val FILE_NAME = "lbc-test"
    }

    abstract fun imageDao(): ImageDao
}
