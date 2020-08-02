package com.dacuesta.architectcoders.presentation.main.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dacuesta.architectcoders.domain.MoviesState
import com.dacuesta.architectcoders.domain.entity.ErrorEntity
import com.dacuesta.architectcoders.domain.entity.movies.MovieEntity
import com.dacuesta.architectcoders.domain.usecase.movies.DeleteFavoritePopularMovie
import com.dacuesta.architectcoders.domain.usecase.movies.GetPopularMovies
import com.dacuesta.architectcoders.domain.usecase.movies.GetFavoritePopularMovies
import com.dacuesta.architectcoders.domain.usecase.movies.InsertFavoritePopularMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber
import java.util.concurrent.atomic.AtomicBoolean

@ExperimentalCoroutinesApi
class PopularMoviesViewModel : ViewModel(), KoinComponent {

    private val getPopularMovies by inject<GetPopularMovies>()
    private val getFavoritePopularMovies by inject<GetFavoritePopularMovies>()
    private val insertFavoritePopularMovies by inject<InsertFavoritePopularMovie>()
    private val deleteFavoritePopularMovies by inject<DeleteFavoritePopularMovie>()

    private val _popularMoviesLD = MutableLiveData<MoviesState<List<MovieEntity>>>()
    val popularMoviesLD: LiveData<MoviesState<List<MovieEntity>>>
        get() = _popularMoviesLD

    private val _favoriteMoviesLD = MutableLiveData<List<MovieEntity>>()
    val favoriteMoviesLD: LiveData<List<MovieEntity>>
        get() = _favoriteMoviesLD

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                getPopularMovies().collect { state ->
                    _popularMoviesLD.postValue(state)
                }
            }
            launch {
                getFavoritePopularMovies().collect { movies ->
                    _favoriteMoviesLD.postValue(movies)
                }
            }
        }
    }

    fun loadMore() {
        viewModelScope.launch(Dispatchers.IO) {
            getPopularMovies().collect { state ->
                _popularMoviesLD.postValue(state)
            }
        }
    }

    @ExperimentalCoroutinesApi
    fun favoriteClicked(movie: MovieEntity, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            if (!isFavorite) {
                insertFavoritePopularMovies(movie)
            } else {
                deleteFavoritePopularMovies(movie)
            }
        }
    }

}