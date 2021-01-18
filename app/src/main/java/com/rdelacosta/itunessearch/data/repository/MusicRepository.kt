package com.rdelacosta.itunessearch.data.repository

import com.rdelacosta.itunessearch.data.entities.Music
import com.rdelacosta.itunessearch.data.local.MusicDao
import com.rdelacosta.itunessearch.data.remote.MusicRemoteDataSource
import javax.inject.Inject

class MusicRepository @Inject constructor(
    private val remoteDataSource: MusicRemoteDataSource,
    private val localDataSource: MusicDao
) {

    fun getMusicListFromDB() = localDataSource.getAllMusic()

    suspend fun searchTerm(term: String) = remoteDataSource.getMusicList(term)

    suspend fun deleteDB() {
        localDataSource.deleteAll()
    }

    suspend fun insertMusicListToDB(musicList: List<Music>) {
        localDataSource.insertAll(musicList)
    }

}