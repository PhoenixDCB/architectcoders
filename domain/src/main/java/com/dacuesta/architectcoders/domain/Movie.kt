package com.dacuesta.architectcoders.domain

data class Movie(
    val id: Int = 0,
    val posterImageUrl: String = "",
    val backdropImageUrl: String = "",
    val title: String = "",
    val releaseDate: String = ""
)