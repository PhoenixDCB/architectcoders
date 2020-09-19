package com.dacuesta.architectcoders.movies.popularmovies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import arrow.core.right
import com.dacuesta.architectcoders.domain.Movie
import com.dacuesta.architectcoders.movie.MovieEntry
import com.dacuesta.architectcoders.navigator.Navigator
import com.dacuesta.architectcoders.usecase.movies.DeleteFavoriteMovie
import com.dacuesta.architectcoders.usecase.movies.GetFavoriteMovies
import com.dacuesta.architectcoders.usecase.movies.GetPopularMovies
import com.dacuesta.architectcoders.usecase.movies.InsertFavoriteMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PopularMoviesViewModelUnitTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var navigator: Navigator

    @Mock
    private lateinit var getPopularMovies: GetPopularMovies

    @Mock
    private lateinit var getFavoriteMovies: GetFavoriteMovies

    @Mock
    private lateinit var insertFavoriteMovie: InsertFavoriteMovie

    @Mock
    private lateinit var deleteFavoriteMovie: DeleteFavoriteMovie

    @Mock
    private lateinit var popularMoviesObserver: Observer<PopularMoviesModel.PopularMovies>

    @Mock
    private lateinit var favoriteMoviesObserver: Observer<PopularMoviesModel.FavoriteMovies>

    private lateinit var viewModel: PopularMoviesViewModel


    private fun setUp() {
        viewModel = PopularMoviesViewModel(
            io = Dispatchers.Unconfined,
            main = Dispatchers.Main,
            navigator = navigator,
            getPopularMovies = getPopularMovies,
            getFavoriteMovies = getFavoriteMovies,
            insertFavoriteMovie = insertFavoriteMovie,
            deleteFavoriteMovie = deleteFavoriteMovie
        )
    }

    @Test
    fun `init gets popular movies`() {
        runBlocking {
            val movies = listOf(Movie(id = 0))
            `when`(getPopularMovies.invoke(false)).thenReturn(movies.right())

            setUp()
            viewModel.popularMoviesLD.observeForever(popularMoviesObserver)

            verify(popularMoviesObserver).onChanged(PopularMoviesModel.PopularMovies(movies))
        }
    }

    @Test
    fun `resumed() gets favorite movies`() {
        runBlocking {
            val popularMovies = listOf(Movie(id = 1))
            val favoriteMovies = listOf(Movie(id = 2))
            `when`(getPopularMovies.invoke(false)).thenReturn(popularMovies.right())
            `when`(getFavoriteMovies.invoke()).thenReturn(favoriteMovies)

            setUp()
            viewModel.resumed()
            viewModel.favoriteMoviesLD.observeForever(favoriteMoviesObserver)

            verify(favoriteMoviesObserver).onChanged(
                PopularMoviesModel.FavoriteMovies(
                    favoriteMovies
                )
            )
        }
    }

    @Test
    fun `refreshClicked() gets popular movies`() {
        runBlocking {
            val movies = listOf(Movie(id = 1))
            `when`(getPopularMovies.invoke(false)).thenReturn(listOf<Movie>().right())
            `when`(getPopularMovies.invoke(true)).thenReturn(movies.right())

            setUp()
            viewModel.refreshClicked()
            viewModel.popularMoviesLD.observeForever(popularMoviesObserver)

            verify(popularMoviesObserver).onChanged(PopularMoviesModel.PopularMovies(movies))
        }
    }

    @Test
    fun `favoriteClicked() inserts popular movie`() {
        runBlocking {
            val movie = Movie(id = 1)
            val movies = listOf(movie)
            `when`(getPopularMovies.invoke(false)).thenReturn(listOf<Movie>().right())
            `when`(insertFavoriteMovie.invoke(movie)).thenReturn(movies)

            setUp()
            viewModel.favoriteClicked(movie, false)
            viewModel.favoriteMoviesLD.observeForever(favoriteMoviesObserver)

            verify(favoriteMoviesObserver).onChanged(PopularMoviesModel.FavoriteMovies(movies))
        }
    }

    @Test
    fun `favoriteClicked() deletes popular movie`() {
        runBlocking {
            val movie = Movie(id = 1)
            val movies = emptyList<Movie>()
            `when`(getPopularMovies.invoke(false)).thenReturn(listOf<Movie>().right())
            `when`(deleteFavoriteMovie.invoke(movie)).thenReturn(movies)

            setUp()
            viewModel.favoriteClicked(movie, true)
            viewModel.favoriteMoviesLD.observeForever(favoriteMoviesObserver)

            verify(favoriteMoviesObserver).onChanged(PopularMoviesModel.FavoriteMovies(movies))
        }
    }

    @Test
    fun `imageClicked() navigates to movie detail`() {
        runBlocking {
            val movie = Movie(id = 1)
            `when`(getPopularMovies.invoke(false)).thenReturn(listOf(movie).right())

            setUp()
            viewModel.imageClicked(movie)

            verify(navigator).navigate(
                PopularMoviesFragmentDirections.actionPopularMoviesToMovie(
                    MovieEntry(movie.id, movie.title)
                )
            )
        }
    }

}