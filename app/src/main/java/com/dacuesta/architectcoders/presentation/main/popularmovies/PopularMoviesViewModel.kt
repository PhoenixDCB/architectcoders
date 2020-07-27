package com.dacuesta.architectcoders.presentation.main.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dacuesta.architectcoders.domain.common.model.movies.Movie
import com.dacuesta.architectcoders.domain.movies.GetPopularMovies
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class PopularMoviesViewModel : ViewModel(), KoinComponent {

    private val getPopularMovies by inject<GetPopularMovies>()

    private val _popularMovies = MutableLiveData<List<Movie>>()
    val popularMovies: LiveData<List<Movie>>
        get() = _popularMovies


    init {
        viewModelScope.launch {
            getPopularMovies("us").collect { result ->
                result.fold({ error ->
                    _popularMovies.value = emptyList()
                }) { metadata ->
                    val movies = metadata.results
                    _popularMovies.value = movies
                }
            }
        }
    }

}