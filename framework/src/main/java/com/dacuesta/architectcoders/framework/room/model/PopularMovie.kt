package com.dacuesta.architectcoders.framework.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PopularMovies")
internal data class PopularMovie(
    @PrimaryKey val id: Int,
    val posterImageUrl: String,
    val backdropImageUrl: String,
    val title: String,
    val releaseDate: String,
    val popularity: Double
)