package com.dacuesta.architectcoders.data.moviedetail.remote

import arrow.core.Either
import com.dacuesta.architectcoders.data.moviedetail.dto.MovieDetail
import com.dacuesta.architectcoders.data.model.Error
import kotlinx.coroutines.flow.Flow

interface MovieDetailRemoteDataSource {

    suspend fun getMovieDetail(id: Int) : Flow<Either<Error, MovieDetail>>

}