package com.dacuesta.architectcoders.data.repository.movies

import com.dacuesta.architectcoders.data.local.movie.MovieLocalDataSource
import com.dacuesta.architectcoders.data.remote.tmdb.TmdbRemoteDataSource
import com.dacuesta.architectcoders.data.repository.mapper.local.map
import com.dacuesta.architectcoders.data.repository.mapper.remote.map
import com.dacuesta.architectcoders.domain.entity.movies.MovieEntity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class MoviesRepository : KoinComponent, CoroutineScope {
    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO

    private val remote by inject<TmdbRemoteDataSource>()
    private val local by inject<MovieLocalDataSource>()

    @ExperimentalCoroutinesApi
    private val _favoritePopularMovies = MutableStateFlow<List<MovieEntity>>(emptyList())

    init {
        launch {
            getFavoritePopularMovies()
        }
    }

    val favoritePopularMovies: StateFlow<List<MovieEntity>>
        get() = _favoritePopularMovies

    suspend fun getPopularMovies(region: String) = remote.getPopularMovies(region)
        .map { either ->
            either.mapLeft(::map).map(::map)
        }

    suspend fun saveFavoritePopularMovie(movie: MovieEntity) {
        local.insert(map(movie))
        getFavoritePopularMovies()
    }

    suspend fun deleteFavoritePopularMovie(movie: MovieEntity) {
        local.delete(map(movie))
        getFavoritePopularMovies()
    }

    private suspend fun getFavoritePopularMovies() {
        local.get().map { movies ->
            movies.map(::map)
        }.collect { movies ->
            _favoritePopularMovies.value = movies
        }
    }
}