package com.dacuesta.architectcoders.movies.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.movies.Movie
import com.dacuesta.architectcoders.domain.movies.MoviesMetadata
import com.dacuesta.architectcoders.mapper.map
import com.dacuesta.architectcoders.usecase.movies.DeleteFavoriteMovie
import com.dacuesta.architectcoders.usecase.movies.GetFavoriteMovies
import com.dacuesta.architectcoders.usecase.movies.GetPopularMovies
import com.dacuesta.architectcoders.usecase.movies.InsertFavoriteMovie
import com.dacuesta.architectcoders.navigator.Navigator
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

    private var page = 1
    private var movies: List<Movie> = emptyList()
    private lateinit var popularMoviesJob: Job

    init {
        viewModelScope.launch(Dispatchers.IO) {
            popularMoviesJob = launch {
                invokeGetPopularMovies()
            }
            launch {
                getFavoriteMovies().collect { movies ->
                    _favoriteMoviesLD.postValue(PopularMoviesModel.FavoriteMovies(movies))
                }
            }
        }
    }

    fun retryClicked() {
        if (popularMoviesJob.isCompleted) {
            popularMoviesJob = viewModelScope.launch(Dispatchers.IO) {
                invokeGetPopularMovies()
            }
        }
    }

    fun endReached() {
        if (popularMoviesJob.isCompleted) {
            popularMoviesJob = viewModelScope.launch(Dispatchers.IO) {
                invokeGetPopularMovies()
            }
        }
    }

    fun refreshClicked() {
        if (popularMoviesJob.isCompleted) {
            popularMoviesJob = viewModelScope.launch(Dispatchers.IO) {
                page = 1
                invokeGetPopularMovies()
            }
        } else {
            _popularMoviesLD.postValue(PopularMoviesModel.PopularMovies.HideRefreshLoader)
        }
    }

    private suspend fun invokeGetPopularMovies() {
        _popularMoviesLD.postValue(PopularMoviesModel.PopularMovies.Loader(movies))
        getPopularMovies(page).fold(::handleError, ::handleSuccess)
    }

    private fun handleError(error: Error) {
        _popularMoviesLD.postValue(PopularMoviesModel.PopularMovies.Result(movies))
        viewModelScope.launch(Dispatchers.Main) {
            navigator.toast(error.toMessage())
        }
    }

    private fun handleSuccess(metadata: MoviesMetadata) {
        if (metadata.page == 1) {
            movies = emptyList()
        }

        page = metadata.page + 1
        movies = movies.toMutableList().apply { addAll(metadata.results) }
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