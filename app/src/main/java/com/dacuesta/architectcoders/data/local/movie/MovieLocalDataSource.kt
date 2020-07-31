package com.dacuesta.architectcoders.data.local.movie

import com.dacuesta.architectcoders.data.local.dto.MovieDTO
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun get(): Flow<List<MovieDTO>>
    fun insert(vararg movies: MovieDTO)
    fun delete(vararg movies: MovieDTO)
}