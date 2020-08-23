package com.dacuesta.architectcoders.framework.source.movies

import arrow.core.Either
import com.dacuesta.architectcoders.data.movies.MoviesRemoteDataSource
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.movies.MoviesMetadata
import com.dacuesta.architectcoders.framework.mapper.map
import com.dacuesta.architectcoders.framework.tmdb.TmdbService
import com.dacuesta.architectcoders.framework.utils.invoke
import org.koin.core.KoinComponent
import org.koin.core.inject

internal class MoviesRemoteDataSourceImpl : MoviesRemoteDataSource, KoinComponent {
    private val tmdbService by inject<TmdbService>()

    override suspend fun getPopularMovies(
        region: String,
        page: Int
    ): Either<Error, MoviesMetadata> =
        invoke {
            tmdbService.getPopularMovies(region, page)
        }.map(::map)
}