package com.dacuesta.architectcoders.domain

data class MovieDetail(
    val title: String = "",
    val backdropImageUrl: String = "",
    val overview: String = "",
    val releaseDate: String = "",
    val genres: List<String> = listOf(),
    val productionCompanies: List<String> = listOf(),
    val budget: Long = 0,
    val revenue: Long = 0,
    val spokenLanguages: List<String> = listOf()
)