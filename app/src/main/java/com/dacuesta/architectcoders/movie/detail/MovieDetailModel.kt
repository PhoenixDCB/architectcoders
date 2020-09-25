package com.dacuesta.architectcoders.movie.detail

import com.dacuesta.architectcoders.domain.MovieDetail

sealed class MovieDetailModel {
    object Loader : MovieDetailModel()
    data class Result(val movie: MovieDetail) : MovieDetailModel()
}