package com.dacuesta.architectcoders

import com.dacuesta.architectcoders.navigator.Navigator
import org.koin.dsl.module

val appModule = module {
    single { Navigator() }
}