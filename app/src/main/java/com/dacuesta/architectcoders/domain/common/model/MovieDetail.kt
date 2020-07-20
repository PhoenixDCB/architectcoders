package com.dacuesta.architectcoders.domain.common.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieDetail(
    val title: String,
    val overview: String = "",
    val releaseDate: String = ""
) : Parcelable