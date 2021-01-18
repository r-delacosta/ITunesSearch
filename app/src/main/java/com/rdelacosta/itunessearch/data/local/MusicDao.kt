package com.rdelacosta.itunessearch.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rdelacosta.itunessearch.data.entities.Music

@Dao
interface MusicDao {

    @Query("SELECT * FROM music_table")
    fun getAllMusic() : LiveData<List<Music>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(musicList: List<Music>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMusic(music: Music)

    @Query("DELETE FROM music_table")
    suspend fun deleteAll()

}