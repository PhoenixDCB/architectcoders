package com.dacuesta.architectcoders.framework.geocoder

import android.content.Context
import android.location.Geocoder
import android.location.Location
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

internal class AppGeoCoder : KoinComponent {
    companion object {
        private const val COUNTRY_CODE_DEFAULT_VALUE = "US"
    }

    private val context by inject<Context>()

    fun getCountryCode(location: Location?) = location?.let {
        val geoCoder = Geocoder(context)
        val addresses = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
        addresses.firstOrNull()?.countryCode ?: COUNTRY_CODE_DEFAULT_VALUE
    } ?: COUNTRY_CODE_DEFAULT_VALUE

}