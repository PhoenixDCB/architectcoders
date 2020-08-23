package com.dacuesta.architectcoders.framework.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Movie")
internal data class Movie(
    @PrimaryKey val id: Int,
    val posterImageUrl: String,
    val backdropImageUrl: String,
    val title: String,
    val releaseDate: String
)