package com.dacuesta.architectcoders.framework.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.dacuesta.architectcoders.framework.room.model.FavoriteMovie

@Dao
internal interface FavoriteMovieDAO {

    @Query("SELECT * FROM FavoriteMovies")
    fun getAll(): List<FavoriteMovie>

    @Insert(onConflict = REPLACE)
    fun insert(vararg movies: FavoriteMovie)

    @Delete
    fun delete(vararg movies: FavoriteMovie)

}