package com.dacuesta.architectcoders.framework.tmdb.model.moviedetail

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
internal data class ProductionCompany(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("logo_path")
    val logoPath: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("origin_country")
    val originCountry: String = ""
)