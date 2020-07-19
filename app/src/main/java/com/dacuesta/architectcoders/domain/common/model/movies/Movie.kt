package com.dacuesta.architectcoders.domain.common.model.movies

data class Movie(
    val id: Int,
    val title: String,
    val overview: String = "",
    val releaseDate: String = ""
)