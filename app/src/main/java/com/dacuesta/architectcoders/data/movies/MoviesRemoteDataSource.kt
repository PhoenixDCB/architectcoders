package com.dacuesta.architectcoders.data.movies

import arrow.core.Either
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.movies.MoviesMetadata

interface MoviesRemoteDataSource {
    suspend fun getPopularMovies(region: String, page: Int): Either<Error, MoviesMetadata>
}