package com.dacuesta.architectcoders.framework.source.movies

import android.content.Context
import arrow.core.Either
import com.dacuesta.architectcoders.data.movies.MoviesRemoteDataSource
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.movies.MoviesMetadata
import com.dacuesta.architectcoders.framework.R
import com.dacuesta.architectcoders.framework.geocoder.AppGeoCoder
import com.dacuesta.architectcoders.framework.location.AppLocation
import com.dacuesta.architectcoders.framework.mapper.map
import com.dacuesta.architectcoders.framework.tmdb.TmdbService
import com.dacuesta.architectcoders.framework.utils.invoke
import org.koin.core.KoinComponent
import org.koin.core.inject

internal class MoviesRemoteDataSourceImpl : MoviesRemoteDataSource, KoinComponent {
    private val context by inject<Context>()
    private val appLocation by inject<AppLocation>()
    private val appGeoCoder by inject<AppGeoCoder>()
    private val tmdbService by inject<TmdbService>()

    private var countryCode: String? = null

    override suspend fun getPopularMovies(page: Int): Either<Error, MoviesMetadata> {
        if (page == 1) {
            countryCode = null
        }

        val region: String = countryCode
            ?: appGeoCoder.getCountryCode(
                location = appLocation.getLastLocation(
                    rationaleMessage = context.getString(R.string.tmdb_permission_rationale_location_message)
                )
            ).apply {
                countryCode = this
            }

        return invoke {
            tmdbService.getPopularMovies(region, page)
        }.map(::map)
    }
}