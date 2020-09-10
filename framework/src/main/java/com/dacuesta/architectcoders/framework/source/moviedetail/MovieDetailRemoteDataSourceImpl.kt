package com.dacuesta.architectcoders.framework.source.moviedetail

import arrow.core.Either
import com.dacuesta.architectcoders.data.moviedetail.MovieDetailRemoteDataSource
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.MovieDetail
import com.dacuesta.architectcoders.framework.mapper.map
import com.dacuesta.architectcoders.framework.tmdb.TmdbService
import com.dacuesta.architectcoders.framework.utils.invoke

internal class MovieDetailRemoteDataSourceImpl(
    private val tmdbService: TmdbService
) : MovieDetailRemoteDataSource {

    override suspend fun getMovieDetail(id: Int): Either<Error, MovieDetail> =
        invoke {
            tmdbService.getMovieDetail(id)
        }.map(::map)
}