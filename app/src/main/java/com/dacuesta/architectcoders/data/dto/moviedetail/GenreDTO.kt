package com.dacuesta.architectcoders.data.dto.moviedetail


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class GenreDTO(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = ""
)