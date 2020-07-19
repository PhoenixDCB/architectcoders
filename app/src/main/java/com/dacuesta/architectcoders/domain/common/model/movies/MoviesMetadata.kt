package com.dacuesta.architectcoders.domain.common.model.movies

data class MoviesMetadata(
    val page: Int,
    val results: List<Movie> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)