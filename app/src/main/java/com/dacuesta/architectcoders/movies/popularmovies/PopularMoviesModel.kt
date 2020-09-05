package com.dacuesta.architectcoders.movies.popularmovies

import com.dacuesta.architectcoders.domain.Movie

sealed class PopularMoviesModel {
    data class Loader(val isVisible: Boolean) : PopularMoviesModel()
    data class PopularMovies(val movies: List<Movie>) : PopularMoviesModel()
    data class FavoriteMovies(val movies: List<Movie>) : PopularMoviesModel()
}