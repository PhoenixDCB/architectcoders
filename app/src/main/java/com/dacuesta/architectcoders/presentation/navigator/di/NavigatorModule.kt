package com.dacuesta.architectcoders.presentation.navigator.di

import com.dacuesta.architectcoders.presentation.navigator.Navigator
import org.koin.dsl.module

val navigatorModule = module {
    single {
        Navigator()
    }
}