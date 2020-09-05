package com.dacuesta.architectcoders.framework.source.movies

import android.content.Context
import arrow.core.Either
import arrow.core.right
import com.dacuesta.architectcoders.data.movies.MoviesRemoteDataSource
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.Movie
import com.dacuesta.architectcoders.framework.R
import com.dacuesta.architectcoders.framework.geocoder.AppGeoCoder
import com.dacuesta.architectcoders.framework.location.AppLocation
import com.dacuesta.architectcoders.framework.mapper.map
import com.dacuesta.architectcoders.framework.tmdb.TmdbService
import com.dacuesta.architectcoders.framework.utils.invoke
import org.koin.core.KoinComponent
import org.koin.core.inject

internal class MoviesRemoteDataSourceImpl : MoviesRemoteDataSource, KoinComponent {
    companion object {
        private const val MOVIES_PER_PAGE = 20
    }

    private val context by inject<Context>()
    private val appLocation by inject<AppLocation>()
    private val appGeoCoder by inject<AppGeoCoder>()
    private val tmdbService by inject<TmdbService>()

    private var countryCode: String? = null

    override suspend fun getCurrentPopularMovies(moviesSize: Int): Either<Error, List<Movie>> {
        val region: String = getRegion()
        var page = 1
        val lastPage = getPage(moviesSize)
        val movies = mutableListOf<Movie>()
        var result: Either<Error, List<Movie>> = movies.right()
        while (page <= lastPage && result.isRight()) {
            result = invoke {
                tmdbService.getPopularMovies(region, page)
            }.map { metadata ->
                movies.apply {
                    addAll(map(metadata))
                }
            }
            page++
        }
        return result
    }

    override suspend fun getNextPopularMovies(moviesSize: Int): Either<Error, List<Movie>> {
        val region: String = if (moviesSize == 0) {
            getRegion()
        } else {
            countryCode ?: getRegion()
        }
        val page = getPage(moviesSize) + 1
        return invoke {
            tmdbService.getPopularMovies(region, page)
        }.map(::map)
    }

    private suspend fun getRegion(): String =
        appGeoCoder.getCountryCode(
            location = appLocation.getLastLocation(
                rationaleMessage = context.getString(R.string.tmdb_permission_rationale_location_message)
            )
        ).apply {
            countryCode = this
        }

    private fun getPage(moviesSize: Int): Int =
        if (moviesSize <= 0) {
            1
        } else {
            moviesSize / MOVIES_PER_PAGE
        }
}