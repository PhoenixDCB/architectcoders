package com.dacuesta.architectcoders.data.remote.dto.movies

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class MoviesMetadataDTO(
    @SerializedName("page")
    val page: Int = 0,
    @SerializedName("results")
    val results: List<MovieDTO> = listOf(),
    @SerializedName("total_pages")
    val totalPages: Int = 0,
    @SerializedName("total_results")
    val totalResults: Int = 0
)