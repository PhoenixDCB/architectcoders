package com.dacuesta.architectcoders.data.movies

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.Movie

class MoviesRepository(
    private val remoteDataSource: MoviesRemoteDataSource,
    private val localDataSource: MoviesLocalDataSource
) {

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

    fun getAllFavoriteMovies(): List<Movie> =
        localDataSource.getAllFavoriteMovies().sortedByDescending { movie ->
            movie.popularity
        }

    fun insertFavoriteMovie(movie: Movie): List<Movie> {
        localDataSource.insertFavoriteMovie(movie)
        return getAllFavoriteMovies()
    }

    fun deleteFavoriteMovie(movie: Movie): List<Movie> {
        localDataSource.deleteFavoriteMovie(movie)
        return getAllFavoriteMovies()
    }

}