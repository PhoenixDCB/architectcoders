package com.dacuesta.architectcoders.domain.moviedetail.model

data class MovieDetail(
    val title: String,
    val overview: String = "",
    val releaseDate: String = ""
)