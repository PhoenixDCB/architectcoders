package com.dacuesta.architectcoders.domain.common.model

data class MovieDetail(
    val title: String,
    val overview: String = "",
    val releaseDate: String = ""
)