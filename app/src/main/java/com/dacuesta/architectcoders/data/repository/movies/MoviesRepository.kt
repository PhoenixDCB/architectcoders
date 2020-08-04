package com.dacuesta.architectcoders.data.repository.movies

import arrow.core.Either
import com.dacuesta.architectcoders.data.local.movie.MovieLocalDataSource
import com.dacuesta.architectcoders.data.remote.tmdb.TmdbRemoteDataSource
import com.dacuesta.architectcoders.data.repository.mapper.local.map
import com.dacuesta.architectcoders.data.repository.mapper.remote.map
import com.dacuesta.architectcoders.domain.entity.ErrorEntity
import com.dacuesta.architectcoders.domain.entity.movies.MovieEntity
import com.dacuesta.architectcoders.domain.entity.movies.MoviesMetadataEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.core.KoinComponent
import org.koin.core.inject

@ExperimentalCoroutinesApi
class MoviesRepository : KoinComponent {
    private val remote by inject<TmdbRemoteDataSource>()
    private val local by inject<MovieLocalDataSource>()

    suspend fun getPopularMovies(
        region: String,
        page: Int
    ): Either<ErrorEntity, MoviesMetadataEntity> =
        remote.getPopularMovies(region, page).mapLeft(::map).map(::map)

    fun getFavoritePopularMovies(): List<MovieEntity> =
        local.get().map(::map)

    fun insertFavoritePopularMovie(movie: MovieEntity) {
        local.insert(map(movie))
    }

    fun deleteFavoritePopularMovie(movie: MovieEntity) {
        local.delete(map(movie))
    }
}