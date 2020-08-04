package com.dacuesta.architectcoders.data.repository.moviedetail

import arrow.core.Either
import com.dacuesta.architectcoders.data.remote.tmdb.TmdbRemoteDataSource
import com.dacuesta.architectcoders.data.repository.mapper.remote.map
import com.dacuesta.architectcoders.domain.entity.ErrorEntity
import com.dacuesta.architectcoders.domain.entity.MovieDetailEntity
import kotlinx.coroutines.flow.map
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieDetailRepository : KoinComponent {
    private val remote by inject<TmdbRemoteDataSource>()

    suspend fun getMovieDetail(id: Int): Either<ErrorEntity, MovieDetailEntity> =
        remote.getMovieDetail(id).mapLeft(::map).map(::map)

}