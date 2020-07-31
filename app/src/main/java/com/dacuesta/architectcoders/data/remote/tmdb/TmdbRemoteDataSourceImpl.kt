package com.dacuesta.architectcoders.data.remote.tmdb

import com.dacuesta.architectcoders.data.remote.extension.result
import com.dacuesta.architectcoders.data.remote.tmdb.service.Service
import org.koin.core.KoinComponent
import org.koin.core.inject

internal class TmdbRemoteDataSourceImpl: TmdbRemoteDataSource, KoinComponent {

    private val service by inject<Service>()

    override suspend fun getPopularMovies(region: String) = service.getPopularMovies(region).result()
    override suspend fun getMovieDetail(id: Int) = service.getMovieDetail(id).result()

}