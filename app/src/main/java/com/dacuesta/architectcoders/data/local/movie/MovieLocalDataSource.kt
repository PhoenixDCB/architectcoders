package com.dacuesta.architectcoders.data.local.movie

import com.dacuesta.architectcoders.data.local.dto.MovieDTO

interface MovieLocalDataSource {
    fun get(): List<MovieDTO>
    fun insert(vararg movies: MovieDTO)
    fun delete(vararg movies: MovieDTO)
}