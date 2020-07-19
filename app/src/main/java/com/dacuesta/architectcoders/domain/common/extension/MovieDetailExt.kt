package com.dacuesta.architectcoders.domain.common.extension

import com.dacuesta.architectcoders.data.common.model.moviedetail.MovieDetail as DataMovieDetail
import com.dacuesta.architectcoders.domain.common.model.MovieDetail

fun DataMovieDetail.map() =
    MovieDetail(
        title = title,
        overview = overview,
        releaseDate = releaseDate
    )
