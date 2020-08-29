package com.dacuesta.architectcoders.framework.source.region

import com.dacuesta.architectcoders.data.region.RegionDataSource
import com.dacuesta.architectcoders.framework.geocoder.AppGeoCoder
import com.dacuesta.architectcoders.framework.location.AppLocation
import org.koin.core.KoinComponent
import org.koin.core.inject

internal class RegionDataSourceImpl : RegionDataSource, KoinComponent {
    private val appLocation by inject<AppLocation>()
    private val appGeoCoder by inject<AppGeoCoder>()

    override suspend fun get(): String {
        val location = appLocation.getLastLocation()
        return appGeoCoder.getCountryCode(location)
    }

}