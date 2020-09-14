package com.dacuesta.architectcoders.data.movies

import arrow.core.right
import com.dacuesta.architectcoders.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesRepositoryUnitTest {

    @Mock
    private lateinit var remoteDataSource: MoviesRemoteDataSource

    @Mock
    private lateinit var localDataSource: MoviesLocalDataSource

    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        moviesRepository = MoviesRepository(
            remoteDataSource = remoteDataSource,
            localDataSource = localDataSource
        )
    }

    @Test
    fun `getPopularMovies(refresh = false) returns remote popular movies`() {
        runBlocking {
            val localMovies = emptyList<Movie>()
            val remoteMovies = listOf(Movie(id = 1))
            `when`(localDataSource.getAllPopularMovies()).thenReturn(localMovies)
            `when`(remoteDataSource.getPopularMovies()).thenReturn(remoteMovies.right())

            val result = moviesRepository.getPopularMovies(false)

            verify(remoteDataSource).getPopularMovies()
            assertEquals(remoteMovies.right(), result)
        }
    }

    @Test
    fun `getPopularMovies(refresh = false) returns local popular movies`() {
        runBlocking {
            val localMovies = listOf(Movie(id = 1))
            `when`(localDataSource.getAllPopularMovies()).thenReturn(localMovies)

            val result = moviesRepository.getPopularMovies(false)

            verify(localDataSource).getAllPopularMovies()
            assertEquals(localMovies.right(), result)
        }
    }

    @Test
    fun `given local empty movies, getPopularMovies(refresh = true) returns remote popular movies`() {
        runBlocking {
            val localMovies = emptyList<Movie>()
            val remoteMovies = listOf(Movie(id = 2))
            `when`(localDataSource.getAllPopularMovies()).thenReturn(localMovies)
            `when`(remoteDataSource.getPopularMovies()).thenReturn(remoteMovies.right())

            val result = moviesRepository.getPopularMovies(true)

            verify(remoteDataSource).getPopularMovies()
            assertEquals(remoteMovies.right(), result)
        }
    }

    @Test
    fun `given not local empty movies, getPopularMovies(refresh = true) returns remote popular movies`() {
        runBlocking {
            val localMovies = listOf(Movie(id = 1))
            val remoteMovies = listOf(Movie(id = 2))
            `when`(localDataSource.getAllPopularMovies()).thenReturn(localMovies)
            `when`(remoteDataSource.getPopularMovies()).thenReturn(remoteMovies.right())

            val result = moviesRepository.getPopularMovies(true)

            verify(remoteDataSource).getPopularMovies()
            assertEquals(remoteMovies.right(), result)
        }
    }

    @Test
    fun `getAllFavoriteMovies() returns favorite movies`() {
        runBlocking {
            val movies = listOf(Movie(id = 1))
            `when`(localDataSource.getAllFavoriteMovies()).thenReturn(movies)

            val result = moviesRepository.getAllFavoriteMovies()

            verify(localDataSource).getAllFavoriteMovies()
            assertEquals(movies, result)
        }
    }

    @Test
    fun `insertFavoriteMovie() inserts favorite movie`() {
        runBlocking {
            val movie = Movie(id = 1)
            val movies = listOf(movie)
            `when`(localDataSource.getAllFavoriteMovies()).thenReturn(movies)

            val result = moviesRepository.insertFavoriteMovie(movie)

            verify(localDataSource).insertFavoriteMovie(movie)
            assertEquals(movies, result)
        }
    }

    @Test
    fun `deleteFavoriteMovie() deletes favorite movie`() {
        runBlocking {
            val movie = Movie(id = 1)
            val movies = emptyList<Movie>()
            `when`(localDataSource.getAllFavoriteMovies()).thenReturn(movies)

            val result = moviesRepository.deleteFavoriteMovie(movie)

            verify(localDataSource).deleteFavoriteMovie(movie)
            assertEquals(movies, result)
        }
    }

}