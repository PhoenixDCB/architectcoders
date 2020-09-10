package com.dacuesta.architectcoders.data.movies

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@Suppress("EXPERIMENTAL_API_USAGE")
class MoviesRepository(
    private val remoteDataSource: MoviesRemoteDataSource,
    private val localDataSource: MoviesLocalDataSource
) : CoroutineScope {

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO

    private val _favoriteMovies = MutableStateFlow<List<Movie>>(listOf())
    val favoriteMovies: StateFlow<List<Movie>>
        get() = _favoriteMovies

    init {
        launch {
            getAllFavoriteMovies()
        }
    }

    suspend fun getPopularMovies(refresh: Boolean): Either<Error, List<Movie>> {
        val localMovies = localDataSource.getAllPopularMovies()
        return if (refresh || localMovies.isEmpty()) {
            remoteDataSource.getPopularMovies().fold(
                { error ->
                    error.left()
                },
                { remoteMovies ->
                    localDataSource.deleteAllPopularMovies()
                    localDataSource.insertPopularMovies(remoteMovies)
                    remoteMovies.sortedByDescending { movie ->
                        movie.popularity
                    }.right()
                }
            )
        } else {
            localMovies.sortedByDescending { movie ->
                movie.popularity
            }.right()
        }
    }

    fun insertFavoriteMovie(movie: Movie) {
        localDataSource.insertFavoriteMovie(movie)
        getAllFavoriteMovies()
    }

    fun deleteFavoriteMovie(movie: Movie) {
        localDataSource.deleteFavoriteMovie(movie)
        getAllFavoriteMovies()
    }

    private fun getAllFavoriteMovies() {
        val movies = localDataSource.getAllFavoriteMovies().sortedByDescending { movie ->
            movie.popularity
        }
        _favoriteMovies.value = movies
    }

}