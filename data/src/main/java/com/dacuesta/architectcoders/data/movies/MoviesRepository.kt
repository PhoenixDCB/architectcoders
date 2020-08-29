package com.dacuesta.architectcoders.data.movies

import arrow.core.Either
import com.dacuesta.architectcoders.data.region.RegionRepository
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.movies.Movie
import com.dacuesta.architectcoders.domain.movies.MoviesMetadata
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.KoinComponent
import org.koin.core.inject

@Suppress("EXPERIMENTAL_API_USAGE")
class MoviesRepository : KoinComponent {
    private val regionRepository by inject<RegionRepository>()
    private val remoteDataSource by inject<MoviesRemoteDataSource>()
    private val localDataSource by inject<MoviesLocalDataSource>()

    private val _favoriteMovies = MutableStateFlow<List<Movie>>(listOf())
    val favoriteMovies : StateFlow<List<Movie>>
        get() = _favoriteMovies

    private var region: String? = null

    init {
        getFavoriteMovies()
    }

    suspend fun getPopularMovies(page: Int): Either<Error, MoviesMetadata> {
        val region: String = this.region ?: regionRepository.get()
        this.region = region
        return remoteDataSource.getPopularMovies (region, page)
    }

    fun insertFavoriteMovie(movie: Movie) {
        localDataSource.insert(movie)
        getFavoriteMovies()
    }

    fun deleteFavoriteMovie(movie: Movie) {
        localDataSource.delete(movie)
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() {
        val movies = localDataSource.get()
        _favoriteMovies.value = movies
    }

}