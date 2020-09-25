package com.dacuesta.architectcoders.data.moviedetail

import arrow.core.Either
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.MovieDetail
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieDetailRepository : KoinComponent {
    private val remoteDataSource by inject<MovieDetailRemoteDataSource>()

    suspend fun getMovieDetail(id: Int): Either<Error, MovieDetail> =
        remoteDataSource.getMovieDetail(id)

}