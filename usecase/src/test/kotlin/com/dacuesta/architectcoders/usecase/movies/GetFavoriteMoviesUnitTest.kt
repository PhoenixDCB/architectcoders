package com.dacuesta.architectcoders.usecase.movies

import com.dacuesta.architectcoders.data.movies.MoviesRepository
import com.dacuesta.architectcoders.domain.Movie
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetFavoriteMoviesUnitTest {

    @Mock
    private lateinit var repository: MoviesRepository

    private lateinit var getFavoriteMovies: GetFavoriteMovies

    @Before
    fun setUp() {
        getFavoriteMovies = GetFavoriteMovies(repository)
    }

    @Test
    fun `invoke() returns favorite movies`() {
        runBlocking {
            val movies = listOf(Movie(id = 1))
            `when`(repository.getAllFavoriteMovies()).thenReturn(movies)

            val result = getFavoriteMovies()

            verify(repository).getAllFavoriteMovies()
            assertEquals(movies, result)
        }
    }

}