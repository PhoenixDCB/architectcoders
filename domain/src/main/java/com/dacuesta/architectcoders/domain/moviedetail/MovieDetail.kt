package com.dacuesta.architectcoders.domain.moviedetail

data class MovieDetail(
    val title: String = "",
    val backdropImageUrl: String = "",
    val overview: String = "",
    val releaseDate: String = "",
    val genres: List<String> = listOf(),
    val productionCompanies: List<String> = listOf(),
    val budget: Int = 0,
    val revenue: Int = 0,
    val spokenLanguages: List<String> = listOf()
)