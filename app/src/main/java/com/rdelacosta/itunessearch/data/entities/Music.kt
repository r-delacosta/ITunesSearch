package com.rdelacosta.itunessearch.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat

@Entity(tableName = "music_table")
data class Music(
    val artistName: String? = "",
    val trackName: String? = "",
    val collectionName: String? = "",
    val artistViewUrl: String? = "",
    val collectionViewUrl: String? = "",
    val trackViewUrl: String? = "",
    val previewUrl: String? = "",
    val artworkUrl30: String? = "",
    val artworkUrl60: String? = "",
    val artworkUrl100: String? = "",
    val trackPrice: String? = "",
    val releaseDate: String? = "",
    val trackExplicitness: String? = "",
    val currency: String? = "",
    val created: Long = System.currentTimeMillis(),
    val primaryGenreName: String? = "",
    @PrimaryKey(autoGenerate = true) val id: Long = 0
) {
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)
}