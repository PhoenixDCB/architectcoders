package com.dacuesta.architectcoders.main.popularmovies

import com.dacuesta.architectcoders.domain.movies.Movie

sealed class PopularMoviesModel {
    sealed class PopularMovies : PopularMoviesModel() {
        data class Loader(val movies: List<Movie>) : PopularMovies()
        data class Result(val movies: List<Movie>) : PopularMovies()
    }

    data class FavoriteMovies(val movies: List<Movie>) : PopularMoviesModel()
}