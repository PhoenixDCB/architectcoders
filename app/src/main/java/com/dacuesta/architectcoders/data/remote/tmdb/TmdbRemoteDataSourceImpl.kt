package com.dacuesta.architectcoders.data.remote.tmdb

import com.dacuesta.architectcoders.data.remote.extension.result
import com.dacuesta.architectcoders.data.remote.tmdb.service.Service

internal class TmdbRemoteDataSourceImpl(
    private val service: Service
) : TmdbRemoteDataSource {

    override suspend fun getPopularMovies(region: String) = service.getPopularMovies(region).result()
    override suspend fun getMovieDetail(id: Int) = service.getMovieDetail(id).result()

}