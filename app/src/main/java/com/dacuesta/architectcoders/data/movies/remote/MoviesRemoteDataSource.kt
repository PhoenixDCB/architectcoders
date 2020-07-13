package com.dacuesta.architectcoders.data.movies.remote

import arrow.core.Either
import com.dacuesta.architectcoders.data.model.Error
import com.dacuesta.architectcoders.data.movies.model.MoviesMetadata
import kotlinx.coroutines.flow.Flow

interface MoviesRemoteDataSource {

    suspend fun getPopularMovies(region: String) : Flow<Either<Error, MoviesMetadata>>

}