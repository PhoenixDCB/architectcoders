package com.dacuesta.architectcoders.data.movies

import com.dacuesta.architectcoders.data.movies.remote.MoviesRemoteDataSource

class MoviesRepository(
    private val remote: MoviesRemoteDataSource
) {

    suspend fun getPopularMovies(region: String) = remote.getPopularMovies(region)

}