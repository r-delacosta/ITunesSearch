package com.rdelacosta.itunessearch.data.remote

import com.rdelacosta.itunessearch.data.entities.MusicList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicService {

    @GET("search")
    suspend fun getMusicList(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String,
        @Query("limit") limit : Int,
    ) : Response<MusicList>

}