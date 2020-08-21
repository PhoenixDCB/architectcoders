package com.dacuesta.architectcoders.presentation.main.popularmovies.adapter

sealed class PopularMoviesItem {
    data class Movie(val movie: com.dacuesta.architectcoders.domain.movies.Movie) : PopularMoviesItem()
    object Loader : PopularMoviesItem()
}