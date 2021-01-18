package com.rdelacosta.itunessearch.ui.musicList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rdelacosta.itunessearch.data.entities.Music
import com.rdelacosta.itunessearch.data.repository.MusicRepository
import kotlinx.coroutines.launch

class MusicsViewModel @ViewModelInject constructor(
        private val repository: MusicRepository
) : ViewModel() {

    var progressBarState = MutableLiveData<Boolean>()
    val musics: LiveData<List<Music>> = repository.getMusicListFromDB()

    fun searchTerm(query: String?) {
        viewModelScope.launch {
            if (query != null) {
                progressBarState.postValue(true)
                val response = repository.searchTerm(query)
                if (response.isSuccessful) {
                    //delete all music from DB first
                    repository.deleteDB()
                    // transform response to List<Music> and save to db
                    response.body()?.results?.let { repository.insertMusicListToDB(it) }
                } else {
                    //error ersponse code
                }
                progressBarState.postValue(false)
            }
        }
    }

}