package com.dacuesta.architectcoders.framework.source.movies

import android.content.Context
import arrow.core.Either
import com.dacuesta.architectcoders.data.movies.MoviesRemoteDataSource
import com.dacuesta.architectcoders.domain.Error
import com.dacuesta.architectcoders.domain.Movie
import com.dacuesta.architectcoders.framework.R
import com.dacuesta.architectcoders.framework.geocoder.AppGeoCoder
import com.dacuesta.architectcoders.framework.location.AppLocation
import com.dacuesta.architectcoders.framework.mapper.map
import com.dacuesta.architectcoders.framework.tmdb.TmdbService
import com.dacuesta.architectcoders.framework.utils.invoke

internal class MoviesRemoteDataSourceImpl(
    private val context: Context,
    private val appLocation: AppLocation,
    private val appGeoCoder: AppGeoCoder,
    private val tmdbService: TmdbService
) : MoviesRemoteDataSource {

    override suspend fun getPopularMovies(): Either<Error, List<Movie>> {
        val region = appGeoCoder.getCountryCode(
            location = appLocation.getLastLocation(
                rationaleMessage = context.getString(R.string.tmdb_permission_rationale_location_message)
            )
        )
        return invoke {
            tmdbService.getPopularMovies(region, 1)
        }.map(::map)
    }
}