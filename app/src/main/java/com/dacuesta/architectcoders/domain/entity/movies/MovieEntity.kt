package com.dacuesta.architectcoders.domain.entity.movies

data class MovieEntity(
    val id: Int,
    val posterImageUrl: String,
    val backdropImageUrl: String,
    val title: String,
    val releaseDate: String
)