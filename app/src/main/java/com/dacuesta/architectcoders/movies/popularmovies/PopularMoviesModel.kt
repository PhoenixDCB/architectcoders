package com.dacuesta.architectcoders.movies.popularmovies

import com.dacuesta.architectcoders.domain.movies.Movie

sealed class PopularMoviesModel {
    sealed class PopularMovies : PopularMoviesModel() {
        data class Loader(val movies: List<Movie>) : PopularMovies()
        data class Result(val movies: List<Movie>) : PopularMovies()
        object HideRefreshLoader : PopularMovies()
    }

    data class FavoriteMovies(val movies: List<Movie>) : PopularMoviesModel()
}