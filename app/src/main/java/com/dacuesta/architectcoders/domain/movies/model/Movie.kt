package com.dacuesta.architectcoders.domain.movies.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String = "",
    val releaseDate: String = ""
)