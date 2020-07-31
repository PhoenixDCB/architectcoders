package com.dacuesta.architectcoders.presentation.main.popularmovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _popularMoviesLD = MutableLiveData<List<MovieEntity>>()
    val popularMoviesLD: LiveData<List<MovieEntity>>
        get() = _popularMoviesLD

    private val _favoriteMoviesLD = MutableLiveData<List<MovieEntity>>()
    val favoriteMoviesLD: LiveData<List<MovieEntity>>
        get() = _favoriteMoviesLD

    private var page = 1
    private var totalPages = 1
    private val isLoadingPopularMovies = AtomicBoolean(false)
    private var movies = emptyList<MovieEntity>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                getPopularMovies()
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
            getPopularMovies()
        }
    }

    private suspend fun getPopularMovies() {
        if (isLoadingPopularMovies.compareAndSet(false, true) && page <= totalPages) {
            getPopularMovies(page).collect { result ->
                result.fold(
                    { error ->
                        _popularMoviesLD.value = emptyList()
                        isLoadingPopularMovies.set(false)
                    },
                    { metadata ->
                        page = metadata.page + 1
                        totalPages = metadata.totalPages
                        movies = movies.toMutableList().apply { addAll(metadata.results) }.toList()
                        _popularMoviesLD.postValue(movies)
                        isLoadingPopularMovies.set(false)
                    }
                )
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