package com.dacuesta.architectcoders.presentation.main.popularmovies

import com.dacuesta.architectcoders.domain.movies.Movie

sealed class PopularMoviesModel {
    sealed class PopularMovies : PopularMoviesModel() {
        data class Loading(val movies: List<Movie>) : PopularMovies()
        data class Result(val movies: List<Movie>) : PopularMovies()
    }

    data class FavoriteMovies(val movies: List<Movie>) : PopularMoviesModel()
}