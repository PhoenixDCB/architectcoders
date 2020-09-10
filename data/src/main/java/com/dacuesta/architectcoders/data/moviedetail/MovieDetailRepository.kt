package com.dacuesta.architectcoders.data.moviedetail

import arrow.core.Either
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.MovieDetail

class MovieDetailRepository(private val remoteDataSource: MovieDetailRemoteDataSource) {

    suspend fun getMovieDetail(id: Int): Either<Error, MovieDetail> =
        remoteDataSource.getMovieDetail(id)

}