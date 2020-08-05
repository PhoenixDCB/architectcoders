package com.dacuesta.architectcoders.data.remote.tmdb

import arrow.core.Either
import com.dacuesta.architectcoders.data.remote.dto.ErrorDTO
import com.dacuesta.architectcoders.data.remote.dto.movies.MoviesMetadataDTO
import com.dacuesta.architectcoders.data.remote.tmdb.service.Service
import com.dacuesta.architectcoders.data.remote.utils.invoke
import org.koin.core.KoinComponent
import org.koin.core.inject

internal class TmdbRemoteDataSourceImpl : TmdbRemoteDataSource, KoinComponent {

    private val service by inject<Service>()


    override suspend fun getPopularMovies(
        region: String,
        page: Int
    ): Either<ErrorDTO, MoviesMetadataDTO> =
        invoke { service.getPopularMovies(region, page) }

    override suspend fun getMovieDetail(id: Int) =
        invoke { service.getMovieDetail(id) }

}