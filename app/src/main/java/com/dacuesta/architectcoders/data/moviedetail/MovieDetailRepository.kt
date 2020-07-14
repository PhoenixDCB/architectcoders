package com.dacuesta.architectcoders.data.moviedetail

import com.dacuesta.architectcoders.data.moviedetail.remote.MovieDetailRemoteDataSource

class MovieDetailRepository(
    private val remote: MovieDetailRemoteDataSource
) {

    suspend fun getMovieDetail(id: Int) = remote.getMovieDetail(id)

}