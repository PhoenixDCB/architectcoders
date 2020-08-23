package com.dacuesta.architectcoders.main.favoritemovies

import com.dacuesta.architectcoders.domain.movies.Movie

sealed class FavoriteMoviesModel {
    data class Movies(val movies: List<Movie>) : FavoriteMoviesModel()
}