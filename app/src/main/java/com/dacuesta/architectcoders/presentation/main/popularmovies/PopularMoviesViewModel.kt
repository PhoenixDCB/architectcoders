package com.dacuesta.architectcoders.presentation.main.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dacuesta.architectcoders.domain.entity.ErrorEntity
import com.dacuesta.architectcoders.domain.entity.movies.MovieEntity
import com.dacuesta.architectcoders.domain.entity.movies.MoviesMetadataEntity
import com.dacuesta.architectcoders.domain.usecase.movies.DeleteFavoritePopularMovie
import com.dacuesta.architectcoders.domain.usecase.movies.GetFavoritePopularMovies
import com.dacuesta.architectcoders.domain.usecase.movies.GetPopularMovies
import com.dacuesta.architectcoders.domain.usecase.movies.InsertFavoritePopularMovie
import com.dacuesta.architectcoders.presentation.navigator.Navigator
import com.dacuesta.architectcoders.presentation.utils.errorMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class PopularMoviesViewModel : ViewModel(), KoinComponent {

    private val navigator by inject<Navigator>()
    private val getPopularMovies by inject<GetPopularMovies>()
    private val getFavoritePopularMovies by inject<GetFavoritePopularMovies>()
    private val insertFavoritePopularMovies by inject<InsertFavoritePopularMovie>()
    private val deleteFavoritePopularMovies by inject<DeleteFavoritePopularMovie>()

    private val _popularMoviesLD = MutableLiveData<PopularMoviesModel.PopularMovies>()
    val popularMoviesLD: LiveData<PopularMoviesModel.PopularMovies>
        get() = _popularMoviesLD

    private val _favoriteMoviesLD = MutableLiveData<PopularMoviesModel.FavoriteMovies>()
    val favoriteMoviesLD: LiveData<PopularMoviesModel.FavoriteMovies>
        get() = _favoriteMoviesLD

    private var page = 1
    private var movies: List<MovieEntity> = emptyList()
    private lateinit var popularMoviesJob: Job

    init {
        viewModelScope.launch(Dispatchers.IO) {
            popularMoviesJob = launch {
                invokeGetPopularMovies()
            }
            launch {
                getFavoritePopularMovies().collect { movies ->
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

    private suspend fun invokeGetPopularMovies() {
        _popularMoviesLD.postValue(PopularMoviesModel.PopularMovies.Loading(movies))
        getPopularMovies(page).fold(::handleError, ::handleSuccess)
    }

    private fun handleError(error: ErrorEntity) {
        _popularMoviesLD.postValue(PopularMoviesModel.PopularMovies.Result(movies))
        viewModelScope.launch(Dispatchers.Main) {
            navigator.showToast(errorMessage(error))
        }
    }

    private fun handleSuccess(metadata: MoviesMetadataEntity) {
        page = metadata.page + 1
        movies = movies.toMutableList().apply { addAll(metadata.results) }
        _popularMoviesLD.postValue(PopularMoviesModel.PopularMovies.Result(movies))
    }

    fun favoriteClicked(movie: MovieEntity, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!isFavorite) {
                insertFavoritePopularMovies(movie)
            } else {
                deleteFavoritePopularMovies(movie)
            }
        }
    }

    fun imageClicked(movie: MovieEntity) {
        TODO("Not yet implemented")
    }

}