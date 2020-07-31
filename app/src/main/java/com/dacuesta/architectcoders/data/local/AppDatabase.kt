package com.dacuesta.architectcoders.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dacuesta.architectcoders.data.local.dto.MovieDTO
import com.dacuesta.architectcoders.data.local.movie.MovieDAO

@Database(entities = [MovieDTO::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDAO(): MovieDAO
}