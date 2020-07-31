package com.dacuesta.architectcoders.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
data class MovieDTO(
    @PrimaryKey val id: Int,
    val imageUrl: String,
    val title: String,
    val releaseDate: String
)