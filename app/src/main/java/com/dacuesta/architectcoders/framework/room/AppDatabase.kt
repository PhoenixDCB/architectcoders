package com.dacuesta.architectcoders.framework.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dacuesta.architectcoders.framework.room.dao.MovieDAO
import com.dacuesta.architectcoders.framework.room.model.Movie

@Database(entities = [Movie::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDAO(): MovieDAO
}