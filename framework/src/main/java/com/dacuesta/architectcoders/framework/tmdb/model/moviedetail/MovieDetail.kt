package com.dacuesta.architectcoders.framework.tmdb.model.moviedetail

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
internal data class MovieDetail(
    @SerializedName("adult")
    val adult: Boolean = false,
    @SerializedName("backdrop_path")
    val backdropPath: String = "",
    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any = Any(),
    @SerializedName("budget")
    val budget: Long = 0,
    @SerializedName("genres")
    val genres: List<Genre> = listOf(),
    @SerializedName("homepage")
    val homepage: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("imdb_id")
    val imdbId: String = "",
    @SerializedName("original_language")
    val originalLanguage: String = "",
    @SerializedName("original_title")
    val originalTitle: String = "",
    @SerializedName("overview")
    val overview: String = "",
    @SerializedName("popularity")
    val popularity: Double = 0.0,
    @SerializedName("poster_path")
    val posterPath: String = "",
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany> = listOf(),
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry> = listOf(),
    @SerializedName("release_date")
    val releaseDate: String = "",
    @SerializedName("revenue")
    val revenue: Long = 0,
    @SerializedName("runtime")
    val runtime: Int = 0,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage> = listOf(),
    @SerializedName("status")
    val status: String = "",
    @SerializedName("tagline")
    val tagline: String = "",
    @SerializedName("title")
    val title: String = "",
    @SerializedName("video")
    val video: Boolean = false,
    @SerializedName("vote_average")
    val voteAverage: Double = 0.0,
    @SerializedName("vote_count")
    val voteCount: Int = 0
)