package com.dacuesta.architectcoders.framework.tmdb.model.moviedetail

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
internal data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso31661: String = "",
    @SerializedName("name")
    val name: String = ""
)