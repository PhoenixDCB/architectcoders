package com.dacuesta.architectcoders.domain.common.model.movies

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val releaseDate: String = ""
) : Parcelable