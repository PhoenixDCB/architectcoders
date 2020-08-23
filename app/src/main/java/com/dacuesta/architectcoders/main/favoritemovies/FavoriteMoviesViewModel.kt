package com.dacuesta.architectcoders.main.favoritemovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dacuesta.architectcoders.domain.movies.Movie
import com.dacuesta.architectcoders.usecase.movies.DeleteFavoriteMovie
import com.dacuesta.architectcoders.usecase.movies.GetFavoriteMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class FavoriteMoviesViewModel : ViewModel(), KoinComponent {

    private val getFavoriteMovies by inject<GetFavoriteMovies>()
    private val deleteFavoriteMovie by inject<DeleteFavoriteMovie>()

    private val _moviesLD = MutableLiveData<FavoriteMoviesModel.Movies>()
    val moviesLD: LiveData<FavoriteMoviesModel.Movies>
        get() = _moviesLD

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteMovies().collect { movies ->
                _moviesLD.postValue(FavoriteMoviesModel.Movies(movies))
            }
        }
    }

    fun imageClicked(movie: Movie) {
        TODO("Not yet implemented")
    }

    fun favoriteClicked(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteMovie(movie)
        }
    }
}