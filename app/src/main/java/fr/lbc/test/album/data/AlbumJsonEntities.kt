package fr.lbc.test.album.data

data class ImageJson(
    val id: Int,
    val albumId: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)
