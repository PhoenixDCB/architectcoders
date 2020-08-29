package com.dacuesta.architectcoders.framework.location.di

import com.dacuesta.architectcoders.framework.location.AppLocation
import org.koin.dsl.module

val appLocationModule = module {
    single {
        AppLocation()
    }
}