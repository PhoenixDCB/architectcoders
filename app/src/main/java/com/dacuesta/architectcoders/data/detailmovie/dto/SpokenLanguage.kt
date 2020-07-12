package com.dacuesta.architectcoders.data.detailmovie.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class SpokenLanguage(
    @SerializedName("iso_639_1")
    val iso6391: String = "",
    @SerializedName("name")
    val name: String = ""
)