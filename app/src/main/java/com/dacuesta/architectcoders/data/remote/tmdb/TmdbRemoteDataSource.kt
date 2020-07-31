package com.dacuesta.architectcoders.data.remote.tmdb

import arrow.core.Either
import com.dacuesta.architectcoders.data.remote.dto.moviedetail.MovieDetailDTO
import com.dacuesta.architectcoders.data.remote.dto.ErrorDTO
import com.dacuesta.architectcoders.data.remote.dto.movies.MoviesMetadataDTO
import kotlinx.coroutines.flow.Flow

interface TmdbRemoteDataSource {

    suspend fun getPopularMovies(region: String) : Flow<Either<ErrorDTO, MoviesMetadataDTO>>
    suspend fun getMovieDetail(id: Int) : Flow<Either<ErrorDTO, MovieDetailDTO>>

}