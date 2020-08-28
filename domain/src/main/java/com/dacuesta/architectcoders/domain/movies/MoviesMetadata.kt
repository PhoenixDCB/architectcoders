package com.dacuesta.architectcoders.domain.movies

data class MoviesMetadata(
    val page: Int = 0,
    val results: List<Movie> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)