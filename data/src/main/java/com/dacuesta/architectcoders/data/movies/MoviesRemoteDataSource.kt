package com.dacuesta.architectcoders.data.movies

import arrow.core.Either
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.Movie

interface MoviesRemoteDataSource {
    suspend fun getCurrentPopularMovies(moviesSize: Int): Either<Error, List<Movie>>
    suspend fun getNextPopularMovies(moviesSize: Int): Either<Error, List<Movie>>
}