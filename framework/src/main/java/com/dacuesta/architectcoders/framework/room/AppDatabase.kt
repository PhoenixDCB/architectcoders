package com.dacuesta.architectcoders.framework.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dacuesta.architectcoders.framework.room.dao.FavoriteMovieDAO
import com.dacuesta.architectcoders.framework.room.dao.PopularMovieDAO
import com.dacuesta.architectcoders.framework.room.model.FavoriteMovie
import com.dacuesta.architectcoders.framework.room.model.PopularMovie

@Database(entities = [PopularMovie::class, FavoriteMovie::class], version = 1)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun popularMovieDAO(): PopularMovieDAO
    abstract fun favoriteMovieDAO(): FavoriteMovieDAO
}