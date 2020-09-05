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
import com.dacuesta.architectcoders.usecase.movies.GetPopularMovies.Method.*
import com.dacuesta.architectcoders.usecase.movies.InsertFavoriteMovie
import com.dacuesta.architectcoders.utils.toMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class PopularMoviesViewModel : ViewModel(), KoinComponent {

    private val navigator by inject<Navigator>()
    private val getPopularMovies by inject<GetPopularMovies>()
    private val getFavoriteMovies by inject<GetFavoriteMovies>()
    private val insertFavoriteMovies by inject<InsertFavoriteMovie>()
    private val deleteFavoriteMovies by inject<DeleteFavoriteMovie>()

    private val _popularMoviesLD = MutableLiveData<PopularMoviesModel.PopularMovies>()
    val popularMoviesLD: LiveData<PopularMoviesModel.PopularMovies>
        get() = _popularMoviesLD

    private val _favoriteMoviesLD = MutableLiveData<PopularMoviesModel.FavoriteMovies>()
    val favoriteMoviesLD: LiveData<PopularMoviesModel.FavoriteMovies>
        get() = _favoriteMoviesLD

    private var movies: List<Movie> = emptyList()
    private lateinit var popularMoviesJob: Job

    init {
        viewModelScope.launch(Dispatchers.IO) {
            popularMoviesJob = launch {
                _popularMoviesLD.postValue(
                    PopularMoviesModel.PopularMovies.Loader(
                        movies = movies,
                        isRefreshing = false
                    )
                )
                getPopularMovies(
                    method = CURRENT_MOVIES
                ).fold(::handleError, ::handleSuccess)
            }
            launch {
                getFavoriteMovies().collect { movies ->
                    _favoriteMoviesLD.postValue(PopularMoviesModel.FavoriteMovies(movies))
                }
            }
        }
    }

    fun refreshClicked() {
        if (popularMoviesJob.isCompleted) {
            popularMoviesJob = viewModelScope.launch(Dispatchers.IO) {
                _popularMoviesLD.postValue(
                    PopularMoviesModel.PopularMovies.Loader(
                        movies = movies,
                        isRefreshing = true
                    )
                )
                getPopularMovies(
                    method = REFRESH_CURRENT_MOVIES
                ).fold(::handleError, ::handleSuccess)
            }
        }
    }

    fun endReached() {
        if (popularMoviesJob.isCompleted) {
            popularMoviesJob = viewModelScope.launch(Dispatchers.IO) {
                _popularMoviesLD.postValue(
                    PopularMoviesModel.PopularMovies.Loader(
                        movies = movies,
                        isRefreshing = false
                    )
                )
                getPopularMovies(
                    method = NEXT_MOVIES
                ).fold(::handleError, ::handleSuccess)
            }
        }
    }

    private fun handleError(error: Error) {
        _popularMoviesLD.postValue(PopularMoviesModel.PopularMovies.Result(movies))
        viewModelScope.launch(Dispatchers.Main) {
            navigator.toast(error.toMessage())
        }
    }

    private fun handleSuccess(movies: List<Movie>) {
        this.movies = movies
        _popularMoviesLD.postValue(PopularMoviesModel.PopularMovies.Result(movies))
    }

    fun favoriteClicked(movie: Movie, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!isFavorite) {
                insertFavoriteMovies(movie)
            } else {
                deleteFavoriteMovies(movie)
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