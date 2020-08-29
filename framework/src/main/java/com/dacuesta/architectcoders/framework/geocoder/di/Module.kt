package com.dacuesta.architectcoders.framework.geocoder.di

import com.dacuesta.architectcoders.framework.geocoder.AppGeoCoder
import org.koin.dsl.module

val appGeoCoderModule = module {
    single {
        AppGeoCoder()
    }
}