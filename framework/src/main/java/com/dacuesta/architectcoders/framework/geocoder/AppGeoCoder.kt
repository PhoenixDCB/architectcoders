package com.dacuesta.architectcoders.framework.geocoder

import android.content.Context
import android.location.Geocoder
import android.location.Location
import org.koin.core.KoinComponent
import org.koin.core.inject
import timber.log.Timber

class AppGeoCoder : KoinComponent {
    companion object {
        private const val DEFAULT_VALUE_COUNTRY_CODE = "US"
    }

    private val context by inject<Context>()

    fun getCountryCode(location: Location?) = (location?.let {
        val geoCoder = Geocoder(context)
        val addresses = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
        addresses.firstOrNull()?.countryCode ?: DEFAULT_VALUE_COUNTRY_CODE
    } ?: DEFAULT_VALUE_COUNTRY_CODE).apply {
        Timber.d("country-code = $this")
    }

}