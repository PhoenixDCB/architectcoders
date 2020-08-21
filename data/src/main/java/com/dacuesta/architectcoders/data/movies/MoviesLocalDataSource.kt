package com.dacuesta.architectcoders.data.movies

import com.dacuesta.architectcoders.domain.movies.Movie

interface MoviesLocalDataSource {
    fun insert(movie: Movie)
    fun delete(movie: Movie)
    fun get(): List<Movie>
}