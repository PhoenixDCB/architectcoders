package com.dacuesta.architectcoders.movies.popularmovies.adapter

import com.dacuesta.architectcoders.domain.Movie

sealed class PopularMoviesItem {
    data class Result(val movie: Movie) : PopularMoviesItem()
    object Loader : PopularMoviesItem()
}