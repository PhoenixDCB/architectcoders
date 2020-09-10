package com.dacuesta.architectcoders.framework.geocoder

import android.content.Context
import android.location.Geocoder
import android.location.Location

internal class AppGeoCoder(
    private val context: Context
) {

    companion object {
        private const val COUNTRY_CODE_DEFAULT_VALUE = "US"
    }

    fun getCountryCode(location: Location?) =
        try {
            location?.let {
                val geoCoder = Geocoder(context)
                val addresses = geoCoder.getFromLocation(location.latitude, location.longitude, 1)
                addresses.firstOrNull()?.countryCode ?: COUNTRY_CODE_DEFAULT_VALUE
            } ?: COUNTRY_CODE_DEFAULT_VALUE
        } catch (e: Exception) {
            COUNTRY_CODE_DEFAULT_VALUE
        }

}