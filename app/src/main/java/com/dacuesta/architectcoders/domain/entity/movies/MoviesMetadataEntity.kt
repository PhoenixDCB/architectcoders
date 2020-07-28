package com.dacuesta.architectcoders.domain.entity.movies

data class MoviesMetadataEntity(
    val page: Int,
    val results: List<MovieEntity> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
)