package com.dacuesta.architectcoders.domain.movies

data class Movie(
    val id: Int,
    val posterImageUrl: String,
    val backdropImageUrl: String,
    val title: String,
    val releaseDate: String
)