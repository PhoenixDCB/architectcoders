package com.dacuesta.architectcoders.data.local.movie

import com.dacuesta.architectcoders.data.local.dto.MovieDTO
import org.koin.core.KoinComponent
import org.koin.core.inject

internal class MovieLocalDataSourceImpl: MovieLocalDataSource, KoinComponent {
    private val dao by inject<MovieDAO>()

    override fun get(): List<MovieDTO> = dao.get()

    override fun insert(vararg movies: MovieDTO) = dao.insert(*movies)

    override fun delete(vararg movies: MovieDTO) = dao.delete(*movies)
}