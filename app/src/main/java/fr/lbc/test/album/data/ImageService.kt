package fr.lbc.test.album.data

import retrofit2.Call
import retrofit2.http.GET

interface ImageService {

    @GET("img/shared/technicaqsdql-test.json")
    fun getImages(): Call<List<ImageJson>>
}
