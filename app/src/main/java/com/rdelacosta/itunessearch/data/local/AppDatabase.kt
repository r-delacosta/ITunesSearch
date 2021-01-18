package com.rdelacosta.itunessearch.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rdelacosta.itunessearch.data.entities.Music

@Database(entities = [Music::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun musicDao() : MusicDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "musics")
                .fallbackToDestructiveMigration()
                .build()
    }

}