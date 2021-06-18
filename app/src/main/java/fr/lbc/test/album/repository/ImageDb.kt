package fr.lbc.test.album.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageDb(
    @PrimaryKey val id: Int,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
