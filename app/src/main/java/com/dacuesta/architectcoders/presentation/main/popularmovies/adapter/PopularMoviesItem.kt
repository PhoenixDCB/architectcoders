package com.dacuesta.architectcoders.presentation.main.popularmovies.adapter

import com.dacuesta.architectcoders.domain.entity.movies.MovieEntity

sealed class PopularMoviesItem {
    data class Movie(val movie: MovieEntity) : PopularMoviesItem()
    object Loader : PopularMoviesItem()
}