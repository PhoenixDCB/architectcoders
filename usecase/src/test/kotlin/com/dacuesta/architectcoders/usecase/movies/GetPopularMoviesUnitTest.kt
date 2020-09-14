package com.dacuesta.architectcoders.usecase.movies

import arrow.core.right
import com.dacuesta.architectcoders.data.movies.MoviesRepository
import com.dacuesta.architectcoders.domain.Movie
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPopularMoviesUnitTest {

    @Mock
    private lateinit var repository: MoviesRepository

    private lateinit var getPopularMovies: GetPopularMovies

    @Before
    fun setUp() {
        getPopularMovies = GetPopularMovies(repository)
    }

    @Test
    fun `invoke() returns popular movies`() {
        runBlocking {
            val movies = listOf(Movie(id = 1))
            `when`(repository.getPopularMovies(anyBoolean())).thenReturn(movies.right())

            val result = getPopularMovies(false)

            verify(repository).getPopularMovies(false)
            assertEquals(movies.right(), result)
        }
    }

}