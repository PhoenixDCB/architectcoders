package com.dacuesta.architectcoders.data.movies

import arrow.core.Either
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.Movie

interface MoviesRemoteDataSource {
    suspend fun getPopularMovies(): Either<Error, List<Movie>>
}