package fr.lbc.test

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.lbc.test.album.repository.AlbumDAO
import fr.lbc.test.album.repository.ImageDb

@Database(
    entities = [
        ImageDb::class
    ],
    version = 1
)
abstract class LBCDatabase : RoomDatabase() {

    companion object {
        const val FILE_NAME = "lbc-test"
    }

    abstract fun albumDAO(): AlbumDAO
}
