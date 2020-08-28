package com.dacuesta.architectcoders.movie.detail

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetailEntry(
    val id: Int,
    val title: String
) : Parcelable