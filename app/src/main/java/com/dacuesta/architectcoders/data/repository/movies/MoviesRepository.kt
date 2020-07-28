package com.dacuesta.architectcoders.data.repository.movies

import com.dacuesta.architectcoders.data.remote.tmdb.TmdbRemoteDataSource
import com.dacuesta.architectcoders.data.repository.mapper.map
import kotlinx.coroutines.flow.map

class MoviesRepository(
    private val remote: TmdbRemoteDataSource
) {

    suspend fun getPopularMovies(region: String) = remote.getPopularMovies(region)
        .map { either ->
            either.mapLeft(::map).map(::map)
        }

}