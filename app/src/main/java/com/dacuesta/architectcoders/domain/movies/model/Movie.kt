package com.dacuesta.architectcoders.domain.movies.model

data class Movie(
    val id: Int,
    val posterPath: String = "",
    val title: String = "",
    val originalTitle: String,
    val overview: String = "",
    val releaseDate: String = ""
)