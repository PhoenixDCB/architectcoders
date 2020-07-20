package com.dacuesta.architectcoders.domain.common.model.movies

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MoviesMetadata(
    val page: Int,
    val results: List<Movie> = listOf(),
    val totalPages: Int = 0,
    val totalResults: Int = 0
) : Parcelable