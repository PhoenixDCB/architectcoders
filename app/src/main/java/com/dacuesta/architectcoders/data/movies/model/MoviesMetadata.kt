package com.dacuesta.architectcoders.data.movies.model

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class MoviesMetadata(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val results: List<Movie> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)