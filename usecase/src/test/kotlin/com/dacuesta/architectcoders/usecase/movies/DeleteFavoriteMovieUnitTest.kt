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
class DeleteFavoriteMovieUnitTest {

    @Mock
    private lateinit var repository: MoviesRepository

    private lateinit var deleteFavoriteMovie: DeleteFavoriteMovie

    @Before
    fun setUp() {
        deleteFavoriteMovie = DeleteFavoriteMovie(repository)
    }

    @Test
    fun `invoke() deletes favorite movie`() {
        runBlocking {
            val movie = Movie(id = 1)
            val movies = emptyList<Movie>()
            `when`(repository.deleteFavoriteMovie(movie)).thenReturn(movies)

            val result = deleteFavoriteMovie(movie)

            verify(repository).deleteFavoriteMovie(movie)
            assertEquals(movies, result)
        }
    }

}