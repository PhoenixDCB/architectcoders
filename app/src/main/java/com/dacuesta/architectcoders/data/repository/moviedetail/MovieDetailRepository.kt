package com.dacuesta.architectcoders.data.repository.moviedetail

import com.dacuesta.architectcoders.data.remote.tmdb.TmdbRemoteDataSource
import com.dacuesta.architectcoders.data.repository.mapper.map
import kotlinx.coroutines.flow.map

class MovieDetailRepository(
    private val remote: TmdbRemoteDataSource
) {

    suspend fun getMovieDetail(id: Int) = remote.getMovieDetail(id)
        .map { either ->
            either.mapLeft(::map).map(::map)
        }

}