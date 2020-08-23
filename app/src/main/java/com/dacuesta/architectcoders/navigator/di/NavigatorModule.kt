package com.dacuesta.architectcoders.navigator.di

import com.dacuesta.architectcoders.navigator.Navigator
import org.koin.dsl.module

val navigatorModule = module {
    single {
        Navigator()
    }
}