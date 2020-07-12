package com.dacuesta.architectcoders.data.movies.remote

import arrow.core.Either
import com.dacuesta.architectcoders.data.dto.Error
import com.dacuesta.architectcoders.data.movies.dto.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRemoteDataSource {

    suspend fun getPopularMovies(region: String) : Flow<Either<Error, List<Movie>>>

}