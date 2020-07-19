package com.dacuesta.architectcoders.data.movies.remote

import arrow.core.Either
import com.dacuesta.architectcoders.data.common.model.Error
import com.dacuesta.architectcoders.data.common.model.movies.MoviesMetadata
import kotlinx.coroutines.flow.Flow

interface MoviesRemoteDataSource {

    suspend fun getPopularMovies(region: String) : Flow<Either<Error, MoviesMetadata>>

}