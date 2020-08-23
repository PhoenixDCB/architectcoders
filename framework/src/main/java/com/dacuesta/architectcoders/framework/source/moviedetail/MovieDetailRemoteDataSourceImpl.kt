package com.dacuesta.architectcoders.framework.source.moviedetail

import arrow.core.Either
import com.dacuesta.architectcoders.data.moviedetail.MovieDetailRemoteDataSource
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.moviedetail.MovieDetail
import com.dacuesta.architectcoders.framework.mapper.map
import com.dacuesta.architectcoders.framework.tmdb.TmdbService
import com.dacuesta.architectcoders.framework.utils.invoke
import org.koin.core.KoinComponent
import org.koin.core.inject

internal class MovieDetailRemoteDataSourceImpl : MovieDetailRemoteDataSource, KoinComponent {
    private val tmdbService by inject<TmdbService>()

    override suspend fun getMovieDetail(id: Int): Either<Error, MovieDetail> =
        invoke {
            tmdbService.getMovieDetail(id)
        }.map(::map)
}