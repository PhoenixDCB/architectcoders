package com.dacuesta.architectcoders.data.moviedetail.remote

import com.dacuesta.architectcoders.data.extension.result

class MovieDetailRemoteDataSourceImpl(
    private val service: MovieDetailService
) : MovieDetailRemoteDataSource {

    override suspend fun getMovieDetail(id: Int) = service.getMovieDetail(id).result()

}