package com.dacuesta.architectcoders.movies.favoritemovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dacuesta.architectcoders.domain.Movie
import com.dacuesta.architectcoders.mapper.map
import com.dacuesta.architectcoders.navigator.Navigator
import com.dacuesta.architectcoders.usecase.movies.DeleteFavoriteMovie
import com.dacuesta.architectcoders.usecase.movies.GetFavoriteMovies
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class FavoriteMoviesViewModel(
    private val io: CoroutineDispatcher,
    private val navigator: Navigator,
    private val getFavoriteMovies: GetFavoriteMovies,
    private val deleteFavoriteMovie: DeleteFavoriteMovie
) : ViewModel() {

    private val _favoriteMoviesLD = MutableLiveData<FavoriteMoviesModel.FavoriteMovies>()
    val favoriteMoviesLD: LiveData<FavoriteMoviesModel.FavoriteMovies>
        get() = _favoriteMoviesLD

    fun resumed() {
        viewModelScope.launch(io) {
            _favoriteMoviesLD.postValue(
                FavoriteMoviesModel.FavoriteMovies(movies = getFavoriteMovies())
            )
        }
    }

    fun imageClicked(movie: Movie) {
        val directions = FavoriteMoviesFragmentDirections.actionFavoriteMoviesToMovie(
            map(movie = movie)
        )
        navigator.navigate(directions = directions)
    }

    fun favoriteClicked(movie: Movie) {
        viewModelScope.launch(io) {
            _favoriteMoviesLD.postValue(
                FavoriteMoviesModel.FavoriteMovies(movies = deleteFavoriteMovie(movie))
            )
        }
    }
}