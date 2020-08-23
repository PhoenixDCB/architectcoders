package com.dacuesta.architectcoders.framework.tmdb.model.moviedetail

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
internal data class Genre(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = ""
)