package fr.lbc.test.album.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveImageList(imageList: List<ImageDb>)

    @Query(
        """
        SELECT *
        FROM ImageDb a
        WHERE rowid IN (
          SELECT rowid FROM ImageDb WHERE a.albumId = albumId LIMIT 4
        )
    """
    )
    fun getAlbumWithPreviewImages(): List<ImageDb>
}
