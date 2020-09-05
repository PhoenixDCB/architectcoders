package com.dacuesta.architectcoders.movies.favoritemovies

import com.dacuesta.architectcoders.domain.Movie

sealed class FavoriteMoviesModel {
    data class FavoriteMovies(val movies: List<Movie>) : FavoriteMoviesModel()
}