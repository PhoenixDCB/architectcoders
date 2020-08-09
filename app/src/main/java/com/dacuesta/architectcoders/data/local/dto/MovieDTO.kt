package com.dacuesta.architectcoders.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
data class MovieDTO(
    @PrimaryKey val id: Int,
    val posterImageUrl: String,
    val backdropImageUrl: String,
    val title: String,
    val releaseDate: String
)