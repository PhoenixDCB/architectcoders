package com.dacuesta.architectcoders.presentation.main.popularmovies.adapter

import com.dacuesta.architectcoders.domain.movies.Movie

sealed class PopularMoviesItem {
    data class Result(val movie: Movie) : PopularMoviesItem()
    object Loader : PopularMoviesItem()
}