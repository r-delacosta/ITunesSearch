package com.rdelacosta.itunessearch.ui.musicList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rdelacosta.itunessearch.data.entities.Music
import com.rdelacosta.itunessearch.data.repository.MusicRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber

class MusicsViewModel @ViewModelInject constructor(
        private val repository: MusicRepository
) : ViewModel() {

    var progressBarState = MutableLiveData<Boolean>()
    val musics: LiveData<List<Music>> = repository.getMusicListFromDB()
    val responseInfo = MutableLiveData<String>()

    fun searchTerm(query: String?) {
        viewModelScope.launch {
            if (query != null) {
                progressBarState.postValue(true)
                try {
                    val response = repository.searchTerm(query)
                    if (response.isSuccessful) {
                        repository.deleteDB()
                        response.body()?.results?.let {
                            repository.insertMusicListToDB(it)
                        }
                    } else {
                        responseInfo.postValue(response.message())
                    }
                } catch (e: HttpException) {
                    Timber.d("HttpException" + e.message)
                    responseInfo.postValue(e.message)
                } catch (e: Throwable) {
                    Timber.d("Throwable" + e.message)
                    responseInfo.postValue(e.message)
                }
                progressBarState.postValue(false)
            }
        }
    }

}