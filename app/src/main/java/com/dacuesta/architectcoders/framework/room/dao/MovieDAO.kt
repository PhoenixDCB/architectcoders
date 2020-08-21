package com.dacuesta.architectcoders.framework.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.dacuesta.architectcoders.framework.room.model.Movie

@Dao
internal interface MovieDAO {

    @Query("SELECT * FROM Movie")
    fun get(): List<Movie>

    @Insert(onConflict = REPLACE)
    fun insert(vararg movies: Movie)

    @Delete
    fun delete(vararg movies: Movie)

}