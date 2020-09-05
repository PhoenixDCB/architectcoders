package com.dacuesta.architectcoders.data.movies

import com.dacuesta.architectcoders.domain.Movie

interface MoviesLocalDataSource {
    fun insertPopularMovies(movies: List<Movie>)
    fun deleteAllPopularMovies()
    fun getAllPopularMovies(): List<Movie>
    fun getPopularMoviesSize(): Int

    fun insertFavoriteMovie(movie: Movie)
    fun deleteFavoriteMovie(movie: Movie)
    fun getAllFavoriteMovies(): List<Movie>
}