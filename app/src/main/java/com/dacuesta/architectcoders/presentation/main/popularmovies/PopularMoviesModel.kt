package com.dacuesta.architectcoders.presentation.main.popularmovies

import com.dacuesta.architectcoders.domain.entity.movies.MovieEntity

sealed class PopularMoviesModel {
    sealed class PopularMovies : PopularMoviesModel() {
        data class Loading(val movies: List<MovieEntity>) : PopularMovies()
        data class Result(val movies: List<MovieEntity>) : PopularMovies()
    }

    data class FavoriteMovies(val movies: List<MovieEntity>) : PopularMoviesModel()
}