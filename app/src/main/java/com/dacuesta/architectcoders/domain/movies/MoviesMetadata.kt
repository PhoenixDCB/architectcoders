package com.dacuesta.architectcoders.domain.movies

data class MoviesMetadata(
    val page: Int,
    val results: List<Movie> = listOf(),
    val totalPages: Int,
    val totalResults: Int
)