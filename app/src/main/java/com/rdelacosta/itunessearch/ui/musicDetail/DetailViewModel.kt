package com.rdelacosta.itunessearch.ui.musicDetail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.rdelacosta.itunessearch.data.entities.Music

class DetailViewModel @ViewModelInject constructor(
        @Assisted private val state: SavedStateHandle
) : ViewModel() {

    val music = state.get<Music>("music")

}