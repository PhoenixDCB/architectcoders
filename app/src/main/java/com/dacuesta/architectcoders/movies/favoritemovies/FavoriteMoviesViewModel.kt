package com.dacuesta.architectcoders.movies.favoritemovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dacuesta.architectcoders.domain.movies.Movie
import com.dacuesta.architectcoders.navigator.Navigator
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
    private val navigator by inject<Navigator>()

    private val _favoriteMoviesLD = MutableLiveData<FavoriteMoviesModel.FavoriteMovies>()
    val favoriteMoviesLD: LiveData<FavoriteMoviesModel.FavoriteMovies>
        get() = _favoriteMoviesLD

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoriteMovies().collect { movies ->
                _favoriteMoviesLD.postValue(FavoriteMoviesModel.FavoriteMovies(movies))
            }
        }
    }

    fun imageClicked(movie: Movie) {
        val directions = FavoriteMoviesFragmentDirections.actionFavoriteMoviesToMovie(movie.id)
        navigator.navigate(directions = directions)
    }

    fun favoriteClicked(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFavoriteMovie(movie)
        }
    }
}