package com.dacuesta.architectcoders.movies.popularmovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dacuesta.architectcoders.data.dataModule
import com.dacuesta.architectcoders.data.movies.MoviesLocalDataSource
import com.dacuesta.architectcoders.data.movies.fakeRemoteMovies
import com.dacuesta.architectcoders.domain.Movie
import com.dacuesta.architectcoders.testAppModule
import com.dacuesta.architectcoders.testFrameworkModule
import com.dacuesta.architectcoders.testMoviesModule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.get
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PopularMoviesViewModelIntegrationTest : AutoCloseKoinTest() {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var popularMoviesObserver: Observer<PopularMoviesModel.PopularMovies>

    @Mock
    private lateinit var favoriteMoviesObserver: Observer<PopularMoviesModel.FavoriteMovies>

    private lateinit var viewModel: PopularMoviesViewModel

    @Before
    fun setUp() {
        startKoin {
            modules(listOf(testFrameworkModule, dataModule, testAppModule, testMoviesModule))
        }
    }

    @Test
    fun `init gets popular movies from remote`() {
        viewModel = get()
        viewModel.popularMoviesLD.observeForever(popularMoviesObserver)

        verify(popularMoviesObserver)
            .onChanged(PopularMoviesModel.PopularMovies(movies = fakeRemoteMovies))
    }

    @Test
    fun `init gets popular movies from local`() {
        val localMovies = listOf(
            Movie(id = 100),
            Movie(id = 101),
            Movie(id = 102),
            Movie(id = 103)
        )
        val localDataSource: MoviesLocalDataSource = get()
        localDataSource.insertPopularMovies(localMovies)

        viewModel = get()
        viewModel.popularMoviesLD.observeForever(popularMoviesObserver)

        verify(popularMoviesObserver)
            .onChanged(PopularMoviesModel.PopularMovies(movies = localMovies))
    }

    @Test
    fun `refreshClicked() gets popular movies from remote`() {
        val localMovies = listOf(
            Movie(id = 100),
            Movie(id = 101),
            Movie(id = 102),
            Movie(id = 103)
        )
        val localDataSource: MoviesLocalDataSource = get()
        localDataSource.insertPopularMovies(localMovies)

        viewModel = get()
        viewModel.refreshClicked()
        viewModel.popularMoviesLD.observeForever(popularMoviesObserver)

        verify(popularMoviesObserver)
            .onChanged(PopularMoviesModel.PopularMovies(movies = fakeRemoteMovies))
    }

    @Test
    fun `resumed() gets favorite movies`() {
        val movie = Movie(id = 1)
        val localDataSource: MoviesLocalDataSource = get()
        localDataSource.insertFavoriteMovie(movie)

        viewModel = get()
        viewModel.resumed()
        viewModel.favoriteMoviesLD.observeForever(favoriteMoviesObserver)

        verify(favoriteMoviesObserver)
            .onChanged(PopularMoviesModel.FavoriteMovies(movies = listOf(movie)))
    }

    @Test
    fun `favoriteClicked() inserts favorite movie`() {
        val movie = Movie(id = 1)

        viewModel = get()
        viewModel.favoriteClicked(movie, false)
        viewModel.favoriteMoviesLD.observeForever(favoriteMoviesObserver)

        verify(favoriteMoviesObserver)
            .onChanged(PopularMoviesModel.FavoriteMovies(movies = listOf(movie)))
    }

    @Test
    fun `favoriteClicked() deletes favorite movie`() {
        val movie = Movie(id = 1)
        val localDataSource: MoviesLocalDataSource = get()
        localDataSource.insertFavoriteMovie(movie)

        viewModel = get()
        viewModel.favoriteClicked(movie, true)
        viewModel.favoriteMoviesLD.observeForever(favoriteMoviesObserver)

        verify(favoriteMoviesObserver)
            .onChanged(PopularMoviesModel.FavoriteMovies(movies = emptyList()))
    }

}