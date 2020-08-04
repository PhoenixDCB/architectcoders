package com.dacuesta.architectcoders.data.remote.tmdb

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.dacuesta.architectcoders.data.remote.dto.ErrorDTO
import com.dacuesta.architectcoders.data.remote.dto.movies.MoviesMetadataDTO
import com.dacuesta.architectcoders.data.remote.extension.result
import com.dacuesta.architectcoders.data.remote.tmdb.service.Service
import com.dacuesta.architectcoders.domain.entity.ErrorEntity
import kotlinx.coroutines.delay
import org.koin.core.KoinComponent
import org.koin.core.inject

internal class TmdbRemoteDataSourceImpl : TmdbRemoteDataSource, KoinComponent {

    private val service by inject<Service>()


    override suspend fun getPopularMovies(
        region: String,
        page: Int
    ): Either<ErrorDTO, MoviesMetadataDTO> =
        service.getPopularMovies(region, page).result()

    override suspend fun getMovieDetail(id: Int) =
        service.getMovieDetail(id).result()

}