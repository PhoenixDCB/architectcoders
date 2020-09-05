package com.dacuesta.architectcoders.data.movies

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.KoinComponent
import org.koin.core.inject

@Suppress("EXPERIMENTAL_API_USAGE")
class MoviesRepository : KoinComponent {
    private val remoteDataSource by inject<MoviesRemoteDataSource>()
    private val localDataSource by inject<MoviesLocalDataSource>()

    private val _favoriteMovies = MutableStateFlow<List<Movie>>(listOf())
    val favoriteMovies: StateFlow<List<Movie>>
        get() = _favoriteMovies

    init {
        getAllFavoriteMovies()
    }

    suspend fun getCurrentPopularMovies(refresh: Boolean): Either<Error, List<Movie>> {
        val localMoviesSize = localDataSource.getPopularMoviesSize()
        return when {
            localMoviesSize == 0 -> {
                remoteDataSource.getNextPopularMovies(localMoviesSize).fold(
                    { error ->
                        error.left()
                    },
                    { remoteMovies ->
                        localDataSource.insertPopularMovies(remoteMovies)
                        localDataSource.getAllPopularMovies().right()
                    }
                )
            }
            refresh -> {
                remoteDataSource.getCurrentPopularMovies(localMoviesSize).fold(
                    { error ->
                        error.left()
                    },
                    { remoteMovies ->
                        localDataSource.deleteAllPopularMovies()
                        localDataSource.insertPopularMovies(remoteMovies)
                        localDataSource.getAllPopularMovies().right()
                    }
                )
            }
            else -> {
                localDataSource.getAllPopularMovies().right()
            }
        }
    }

    suspend fun getNextPopularMovies(): Either<Error, List<Movie>> {
        val localMovies = localDataSource.getAllPopularMovies()
        return remoteDataSource.getNextPopularMovies(localMovies.size).fold(
            { error ->
                error.left()
            },
            { remoteMovies ->
                localDataSource.insertPopularMovies(remoteMovies)
                localDataSource.getAllPopularMovies().right()
            }
        )
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
        val movies = localDataSource.getAllFavoriteMovies()
        _favoriteMovies.value = movies
    }

}