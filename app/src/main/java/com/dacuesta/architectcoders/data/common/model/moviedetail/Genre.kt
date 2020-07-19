package com.dacuesta.architectcoders.data.common.model.moviedetail


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Genre(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = ""
)