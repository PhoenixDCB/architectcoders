package com.dacuesta.architectcoders.movie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieEntry(
    val id: Int,
    val title: String
) : Parcelable