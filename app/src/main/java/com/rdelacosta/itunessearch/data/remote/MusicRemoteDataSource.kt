package com.rdelacosta.itunessearch.data.remote

import javax.inject.Inject

class MusicRemoteDataSource @Inject constructor(
    private val musicService: MusicService
) {

    private val country = "ph"
    private val media = "music"
    private val limit = 60

    suspend fun getMusicList(searchTerm: String) = musicService.getMusicList(searchTerm, country, media, limit)
}