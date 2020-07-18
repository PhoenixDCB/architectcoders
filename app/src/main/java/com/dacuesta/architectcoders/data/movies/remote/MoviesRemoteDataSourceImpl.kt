package com.dacuesta.architectcoders.data.movies.remote

import com.dacuesta.architectcoders.data.extension.result

class MoviesRemoteDataSourceImpl(
    private val service: MoviesService
) : MoviesRemoteDataSource {

    override suspend fun getPopularMovies(region: String) = service.getPopularMovies(region).result()

}