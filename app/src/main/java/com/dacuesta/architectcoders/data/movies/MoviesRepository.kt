package com.dacuesta.architectcoders.data.movies

import arrow.core.Either
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.movies.Movie
import com.dacuesta.architectcoders.domain.movies.MoviesMetadata
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.KoinComponent
import org.koin.core.inject

@Suppress("EXPERIMENTAL_API_USAGE")
class MoviesRepository : KoinComponent {
    private val remote by inject<MoviesRemoteDataSource>()
    private val local by inject<MoviesLocalDataSource>()

    private val _favoriteMovies = MutableStateFlow<List<Movie>>(listOf())
    val favoriteMovies : StateFlow<List<Movie>>
        get() = _favoriteMovies

    init {
        getFavoriteMovies()
    }

    suspend fun getPopularMovies(
        region: String,
        page: Int
    ): Either<Error, MoviesMetadata> =
        remote.getPopularMovies(region, page)

    fun insertFavoriteMovie(movie: Movie) {
        local.insert(movie)
        getFavoriteMovies()
    }

    fun deleteFavoriteMovie(movie: Movie) {
        local.delete(movie)
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() {
        val movies = local.get()
        _favoriteMovies.value = movies
    }

}