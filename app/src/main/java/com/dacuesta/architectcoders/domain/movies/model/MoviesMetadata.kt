package com.dacuesta.architectcoders.domain.movies.model

data class MoviesMetadata(
    val page: Int,
    val results: List<Movie> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)