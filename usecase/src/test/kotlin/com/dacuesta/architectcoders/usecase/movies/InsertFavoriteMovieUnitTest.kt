package com.dacuesta.architectcoders.usecase.movies

import com.dacuesta.architectcoders.data.movies.MoviesRepository
import com.dacuesta.architectcoders.domain.Movie
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class InsertFavoriteMovieUnitTest {

    @Mock
    private lateinit var repository: MoviesRepository

    private lateinit var insertFavoriteMovie: InsertFavoriteMovie

    @Before
    fun setUp() {
        insertFavoriteMovie = InsertFavoriteMovie(repository)
    }

    @Test
    fun `invoke() inserts favorite movie`() {
        runBlocking {
            val movie = Movie(id = 1)
            val movies = listOf(movie)
            `when`(repository.insertFavoriteMovie(movie)).thenReturn(movies)

            val result = insertFavoriteMovie(movie)

            verify(repository).insertFavoriteMovie(movie)
            assertEquals(movies, result)
        }
    }

}