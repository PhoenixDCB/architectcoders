package com.dacuesta.architectcoders.data.movies

import arrow.core.Either
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.movies.MoviesMetadata

interface MoviesRemoteDataSource {
    suspend fun getPopularMovies(page: Int): Either<Error, MoviesMetadata>
}