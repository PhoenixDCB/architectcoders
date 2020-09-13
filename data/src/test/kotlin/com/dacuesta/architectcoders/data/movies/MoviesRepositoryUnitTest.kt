package com.dacuesta.architectcoders.data.movies

import arrow.core.right
import com.dacuesta.architectcoders.domain.Movie
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
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

    @After
    fun tearDown() {

    }

    @Test
    fun `given local returning empty movies, getPopularMovies(refresh = false) calls remote-getPopularMovies()`() {
        runBlocking {
            val localMovies = emptyList<Movie>()
            val remoteMovies = listOf(Movie(id = 1))
            `when`(localDataSource.getAllPopularMovies()).thenReturn(localMovies)
            `when`(remoteDataSource.getPopularMovies()).thenReturn(remoteMovies.right())

            moviesRepository.getPopularMovies(false)

            verify(remoteDataSource).getPopularMovies()
        }
    }

    @Test
    fun `given local returning empty movies, getPopularMovies(refresh = true) calls remote-getPopularMovies()`() {
        runBlocking {
            val localMovies = emptyList<Movie>()
            val remoteMovies = listOf(Movie(id = 1))
            `when`(localDataSource.getAllPopularMovies()).thenReturn(localMovies)
            `when`(remoteDataSource.getPopularMovies()).thenReturn(remoteMovies.right())

            moviesRepository.getPopularMovies(true)

            verify(remoteDataSource).getPopularMovies()
        }
    }

    @Test
    fun `given local returning not empty movies, getPopularMovies(refresh = false) calls local-getAllPopularMovies()`() {
        runBlocking {
            val localMovies = listOf(Movie(id = 1))
            `when`(localDataSource.getAllPopularMovies()).thenReturn(localMovies)

            moviesRepository.getPopularMovies(false)

            verify(localDataSource).getAllPopularMovies()
        }
    }

    @Test
    fun `given local returning not empty movies, getPopularMovies(refresh = true) calls remote-getPopularMovies()`() {
        runBlocking {
            val localMovies = listOf(Movie(id = 1))
            val remoteMovies = listOf(Movie(id = 2))
            `when`(localDataSource.getAllPopularMovies()).thenReturn(localMovies)
            `when`(remoteDataSource.getPopularMovies()).thenReturn(remoteMovies.right())

            moviesRepository.getPopularMovies(true)

            verify(remoteDataSource).getPopularMovies()
        }
    }

    @Test
    fun `init calls local-getAllFavoriteMovies()`() {
        verify(localDataSource).getAllFavoriteMovies()
    }

    @Test
    fun `insertFavoriteMovie() calls local-insertFavoriteMovie()`() {
        runBlocking {
            val movie = Movie(id = 1)

            moviesRepository.insertFavoriteMovie(movie)

            verify(localDataSource).insertFavoriteMovie(movie)
        }
    }

    @Test
    fun `insertFavoriteMovie() calls local-getAllFavoriteMovies()`() {
        runBlocking {
            val movie = Movie(id = 1)

            moviesRepository.insertFavoriteMovie(movie)

            verify(localDataSource, times(2)).getAllFavoriteMovies()
        }
    }

    @Test
    fun `deleteFavoriteMovie() calls local-deleteFavoriteMovie()`() {
        runBlocking {
            val movie = Movie(id = 1)

            moviesRepository.deleteFavoriteMovie(movie)

            verify(localDataSource).deleteFavoriteMovie(movie)
        }
    }

    @Test
    fun `deleteFavoriteMovie() calls local-getAllFavoriteMovies()`() {
        runBlocking {
            val movie = Movie(id = 1)

            moviesRepository.deleteFavoriteMovie(movie)

            verify(localDataSource, times(2)).getAllFavoriteMovies()
        }
    }

}