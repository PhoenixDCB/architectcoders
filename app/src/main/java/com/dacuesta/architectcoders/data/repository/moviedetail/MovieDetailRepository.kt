package com.dacuesta.architectcoders.data.repository.moviedetail

import com.dacuesta.architectcoders.data.remote.tmdb.TmdbRemoteDataSource
import com.dacuesta.architectcoders.data.repository.mapper.remote.map
import kotlinx.coroutines.flow.map
import org.koin.core.KoinComponent
import org.koin.core.inject

class MovieDetailRepository : KoinComponent {
    private val remote by inject<TmdbRemoteDataSource>()

    suspend fun getMovieDetail(id: Int) = remote.getMovieDetail(id)
        .map { either ->
            either.mapLeft(::map).map(::map)
        }

}