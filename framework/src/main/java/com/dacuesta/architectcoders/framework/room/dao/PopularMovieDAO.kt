package com.dacuesta.architectcoders.framework.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.dacuesta.architectcoders.framework.room.model.FavoriteMovie
import com.dacuesta.architectcoders.framework.room.model.PopularMovie

@Dao
internal interface PopularMovieDAO {

    @Query("SELECT * FROM PopularMovies")
    fun getAll(): List<PopularMovie>

    @Query("SELECT COUNT(id) FROM PopularMovies")
    fun getSize(): Int

    @Insert(onConflict = REPLACE)
    fun insert(vararg movies: PopularMovie)

    @Query("DELETE FROM PopularMovies")
    fun deleteAll()

}