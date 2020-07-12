package com.dacuesta.architectcoders.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dacuesta.architectcoders.data.movies.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel : ViewModel(), KoinComponent {

    private val moviesRepository by inject<MoviesRepository>()

    init {
        viewModelScope.launch {
            moviesRepository.getPopularMovies("US")
                .flowOn(Dispatchers.IO)
                .collect {
                    Log.d("MUAHAHAHA", "$it")
                }
        }
    }

    fun asdf() {}

}