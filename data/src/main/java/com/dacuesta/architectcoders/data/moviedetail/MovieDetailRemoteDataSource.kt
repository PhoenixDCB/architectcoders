package com.dacuesta.architectcoders.data.moviedetail

import arrow.core.Either
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.moviedetail.MovieDetail

interface MovieDetailRemoteDataSource {
    suspend fun getMovieDetail(id: Int): Either<Error, MovieDetail>
}