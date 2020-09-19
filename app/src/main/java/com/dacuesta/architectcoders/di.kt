package com.dacuesta.architectcoders

import com.dacuesta.architectcoders.navigator.Navigator
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single { Dispatchers.IO }
    single { Dispatchers.Main }

    single { Navigator(context = androidContext()) }
}