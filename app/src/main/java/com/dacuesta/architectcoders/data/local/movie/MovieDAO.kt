package com.dacuesta.architectcoders.data.local.movie

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.*
import androidx.room.Query
import com.dacuesta.architectcoders.data.local.dto.MovieDTO
import kotlinx.coroutines.flow.Flow

@Dao
internal interface MovieDAO {

    @Query("SELECT * FROM Movie")
    fun get(): Flow<List<MovieDTO>>

    @Insert(onConflict = REPLACE)
    fun insert(vararg movies: MovieDTO)

    @Delete
    fun delete(vararg movies: MovieDTO)

}