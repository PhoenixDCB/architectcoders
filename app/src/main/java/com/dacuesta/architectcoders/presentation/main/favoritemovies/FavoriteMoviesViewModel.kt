package com.dacuesta.architectcoders.presentation.main.favoritemovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dacuesta.architectcoders.domain.entity.movies.MovieEntity
import com.dacuesta.architectcoders.domain.usecase.movies.DeleteFavoritePopularMovie
import com.dacuesta.architectcoders.domain.usecase.movies.GetFavoritePopularMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class FavoriteMoviesViewModel : ViewModel(), KoinComponent {

    private val getFavoriteMovies by inject<GetFavoritePopularMovies>()
    private val deleteFavoriteMovie by inject<DeleteFavoritePopularMovie>()

    private val _moviesLD = MutableLiveData<List<MovieEntity>>()
    val moviesLD: LiveData<List<MovieEntity>>
        get() = _moviesLD

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteMovies().collect { movies ->
                _moviesLD.postValue(movies)
            }
        }
    }

    fun imageClicked(movie: MovieEntity) {
        TODO("Not yet implemented")
    }

    fun favoriteClicked(movie: MovieEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteMovie(movie)
        }
    }
}