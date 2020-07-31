package com.dacuesta.architectcoders.domain.entity.movies

data class MovieEntity(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val releaseDate: String
)