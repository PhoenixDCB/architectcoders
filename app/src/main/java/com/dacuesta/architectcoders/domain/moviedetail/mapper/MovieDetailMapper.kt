package com.dacuesta.architectcoders.domain.moviedetail.mapper

import com.dacuesta.architectcoders.data.moviedetail.dto.MovieDetail as DataMovieDetail
import com.dacuesta.architectcoders.domain.moviedetail.model.MovieDetail

fun DataMovieDetail.map() = MovieDetail(
    title = title,
    overview = overview,
    releaseDate = releaseDate
)
