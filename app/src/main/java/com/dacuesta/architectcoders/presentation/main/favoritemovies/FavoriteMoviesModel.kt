package com.dacuesta.architectcoders.presentation.main.favoritemovies

import com.dacuesta.architectcoders.domain.movies.Movie

sealed class FavoriteMoviesModel {
    data class Movies(val movies: List<Movie>) : FavoriteMoviesModel()
}