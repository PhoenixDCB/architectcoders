package com.dacuesta.architectcoders.framework.tmdb.model.moviedetail

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
internal data class SpokenLanguage(
    @SerializedName("iso_639_1")
    val iso6391: String = "",
    @SerializedName("name")
    val name: String = ""
)