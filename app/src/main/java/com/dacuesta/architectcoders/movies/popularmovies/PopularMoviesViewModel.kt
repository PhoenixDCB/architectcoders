package com.dacuesta.architectcoders.movies.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.Movie
import com.dacuesta.architectcoders.mapper.map
import com.dacuesta.architectcoders.navigator.Navigator
import com.dacuesta.architectcoders.usecase.movies.DeleteFavoriteMovie
import com.dacuesta.architectcoders.usecase.movies.GetFavoriteMovies
import com.dacuesta.architectcoders.usecase.movies.GetPopularMovies
import com.dacuesta.architectcoders.usecase.movies.InsertFavoriteMovie
import com.dacuesta.architectcoders.utils.toMessage
import kotlinx.coroutines.*

class PopularMoviesViewModel(
    private val io: CoroutineDispatcher,
    private val main: MainCoroutineDispatcher,
    private val navigator: Navigator,
    private val getPopularMovies: GetPopularMovies,
    private val getFavoriteMovies: GetFavoriteMovies,
    private val insertFavoriteMovie: InsertFavoriteMovie,
    private val deleteFavoriteMovie: DeleteFavoriteMovie
) : ViewModel() {

    private val _loaderLD = MutableLiveData<PopularMoviesModel.Loader>()
    val loaderLD: LiveData<PopularMoviesModel.Loader>
        get() = _loaderLD

    private val _popularMoviesLD = MutableLiveData<PopularMoviesModel.PopularMovies>()
    val popularMoviesLD: LiveData<PopularMoviesModel.PopularMovies>
        get() = _popularMoviesLD

    private val _favoriteMoviesLD = MutableLiveData<PopularMoviesModel.FavoriteMovies>()
    val favoriteMoviesLD: LiveData<PopularMoviesModel.FavoriteMovies>
        get() = _favoriteMoviesLD

    private var popularMoviesJob: Job

    init {
        popularMoviesJob = viewModelScope.launch(io) {
            _loaderLD.postValue(PopularMoviesModel.Loader(isVisible = true))
            getPopularMovies(refresh = false).fold(::handleError, ::handleSuccess)
        }
    }

    fun resumed() {
        viewModelScope.launch(io) {
            _favoriteMoviesLD.postValue(
                PopularMoviesModel.FavoriteMovies(movies = getFavoriteMovies())
            )
        }
    }

    fun refreshClicked() {
        if (popularMoviesJob.isCompleted) {
            popularMoviesJob = viewModelScope.launch(io) {
                _loaderLD.postValue(PopularMoviesModel.Loader(isVisible = true))
                getPopularMovies(refresh = true).fold(::handleError, ::handleSuccess)
            }
        }
    }

    private fun handleError(error: Error) {
        _loaderLD.postValue(PopularMoviesModel.Loader(isVisible = false))
        viewModelScope.launch(main) {
            navigator.toast(error.toMessage())
        }
    }

    private fun handleSuccess(movies: List<Movie>) {
        _loaderLD.postValue(PopularMoviesModel.Loader(isVisible = false))
        _popularMoviesLD.postValue(PopularMoviesModel.PopularMovies(movies))
    }

    fun favoriteClicked(movie: Movie, isFavorite: Boolean) {
        viewModelScope.launch(io) {
            if (!isFavorite) {
                _favoriteMoviesLD.postValue(
                    PopularMoviesModel.FavoriteMovies(insertFavoriteMovie(movie = movie))
                )
            } else {
                _favoriteMoviesLD.postValue(
                    PopularMoviesModel.FavoriteMovies(deleteFavoriteMovie(movie = movie))
                )
            }
        }
    }

    fun imageClicked(movie: Movie) {
        val directions = PopularMoviesFragmentDirections.actionPopularMoviesToMovie(
            map(movie = movie)
        )
        navigator.navigate(directions = directions)
    }

}