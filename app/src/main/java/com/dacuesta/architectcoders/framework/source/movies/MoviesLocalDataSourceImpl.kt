package com.dacuesta.architectcoders.framework.source.movies

import com.dacuesta.architectcoders.data.movies.MoviesLocalDataSource
import com.dacuesta.architectcoders.domain.movies.Movie as DomainMovie
import com.dacuesta.architectcoders.framework.mapper.map
import com.dacuesta.architectcoders.framework.room.dao.MovieDAO
import org.koin.core.KoinComponent
import org.koin.core.inject

class MoviesLocalDataSourceImpl : MoviesLocalDataSource, KoinComponent {
    private val movieDao by inject<MovieDAO>()

    override fun insert(movie: DomainMovie) {
        val roomMovie = map(movie)
        movieDao.insert(roomMovie)
    }

    override fun delete(movie: DomainMovie) {
        val roomMovie = map(movie)
        movieDao.delete(roomMovie)
    }

    override fun get(): List<DomainMovie> =
        movieDao.get().map(::map)
}